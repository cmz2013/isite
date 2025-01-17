package org.isite.data.handler;

import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.sign.SignSecret;
import org.isite.data.exception.ServerException;
import org.isite.data.log.LogHandler;
import org.isite.data.support.dto.DataLogDto;
import org.isite.data.support.vo.DataApi;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Class.forName;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.isite.commons.cloud.utils.ResultUtils.isOk;
import static org.isite.commons.lang.Assert.notNull;
import static org.isite.commons.lang.utils.TypeUtils.cast;
import static org.isite.data.client.DataApiAccessor.callApi;
import static org.isite.data.support.constants.DataConstants.SERVICE_ID;
import static org.isite.data.support.enums.WsType.SERVICE;

/**
 * @Description 封装服务接口数据处理过程
 * @param <P> 参数类
 * @param <R> 结果类
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class DataHandler<P, R> implements InitializingBean {

	private SignSecret signSecret;
	private LogHandler logHandler;
	/**
	 * 处理请求数据，返回响应结果
	 */
	private final WsFunction<P, R> wsFunction;

	protected DataHandler(WsFunction<P, R> wsFunction) {
		this.wsFunction = wsFunction;
	}

	/**
	 * @Description 处理日志数据
	 * @param logDto 接口日志
	 */
	public DataLogDto handle(DataLogDto logDto) {
		try {
			P reqData = null;
			if (isNotBlank(logDto.getReqData())) {
				reqData = toData(logDto.getReqData(), cast(forName(logDto.getParamClass())));
			}
			Result<R> result = this.wsFunction.apply(reqData);
			logDto.setStatus(isOk(result));
			logDto.setRepData(formatResult(result));
			logHandler.handle(logDto);
		} catch (Exception e) {
			logHandler.handle(logDto, e);
		}
		return logDto;
	}

	/**
	 * 处理接口请求
	 */
	public String handle(DataApi dataApi, P data) throws ServerException {
		DataLogDto logDto = new DataLogDto(dataApi.getAppCode(), dataApi.getId(),
				this.getClass().getName(), data.getClass().getTypeName(), formatData(data));
		try {
			Result<R> result = this.wsFunction.apply(data);
			logDto.setStatus(isOk(result));
			logDto.setRepData(formatResult(result));
			logHandler.handle(logDto);
			return logDto.getRepData();
		} catch (Exception e) {
			logHandler.handle(logDto, e);
			throw new ServerException(e);
		}
	}

	/**
	 * @Description 根据id查询数据接口
	 */
	protected DataApi getDataApi(String apiId) {
		return callApi(SERVICE, apiId, signSecret.password(SERVICE_ID));
	}

	/**
	 * 将result转换为字符串
	 * @param result 响应结果
	 */
	protected abstract String formatResult(Result<R> result);

	/**
	 * 将data转换为字符串
	 * @param data 请求参数
	 */
	protected abstract String formatData(P data);

	/**
	 * 封装pClass的数据实例
	 * @param data 请求数据
	 * @param pClass 参数类
	 */
	protected abstract P toData(String data, Class<P> pClass);

	@Autowired
	public void setSignSecret(SignSecret signSecret) {
		this.signSecret = signSecret;
	}

	@Autowired
	public void setLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		notNull(this.signSecret, "signSecret must be set");
		notNull(this.logHandler, "logHandler must be set");
	}
}