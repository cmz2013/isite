package org.isite.commons.cloud.data;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Result;

import java.util.List;

import static org.isite.commons.lang.http.HttpStatus.OK;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ListResult<T> extends Result<List<T>> {
    /**
     * 查询条件
     */
    private ListRequest<?> request;

    public ListResult(ListRequest<?> request, int code, List<T> list) {
        super(code, list);
        this.request = request;
    }

    public static <T> ListResult<T> success(ListRequest<?> request, List<T> list) {
        return new ListResult<>(request, OK.getCode(), list);
    }
}

