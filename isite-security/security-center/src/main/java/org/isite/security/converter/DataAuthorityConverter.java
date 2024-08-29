package org.isite.security.converter;

import org.isite.security.data.vo.DataAuthority;
import org.isite.tenant.data.vo.DataApi;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyMap;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * @author <font color='blue'>zhangcm</font>
 */
public class DataAuthorityConverter {

    private DataAuthorityConverter() {
    }

    /**
     * key: serviceId; value: 数据接口权限,Set集合自动去重
     */
    public static Map<String, Set<DataAuthority>> toDataAuthority(Set<DataApi> dataApis) {
        if (isEmpty(dataApis)) {
            return emptyMap();
        }
        Map<String, Set<DataAuthority>> authorities = new HashMap<>();
        dataApis.forEach(api -> authorities.computeIfAbsent(api.getServiceId(), k -> new HashSet<>())
                .add(new DataAuthority(api.getMethod(), api.getRequestPath())));
        return authorities;
    }
}
