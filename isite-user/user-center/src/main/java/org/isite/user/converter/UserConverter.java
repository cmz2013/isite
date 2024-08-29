package org.isite.user.converter;

import org.isite.user.data.dto.UserDto;
import org.isite.user.po.UserPo;

import static java.lang.Boolean.FALSE;
import static org.isite.commons.cloud.data.Converter.convert;
import static org.isite.commons.lang.data.Constants.BLANK_STRING;
import static org.isite.commons.lang.enums.SwitchStatus.ENABLED;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class UserConverter {

    private UserConverter() {
    }

    public static UserPo toUserPo(UserDto userDto) {
        UserPo userPo = convert(userDto, UserPo::new);
        userPo.setStatus(ENABLED);
        userPo.setInternal(FALSE);
        userPo.setRemark(BLANK_STRING);
        userPo.setPassword(BLANK_STRING);
        if (null == userPo.getEmail()) {
            userPo.setEmail(BLANK_STRING);
        }
        return userPo;
    }

    public static UserPo toUserSelectivePo(UserDto userDto) {
        UserPo userPo = convert(userDto, UserPo::new);
        if (null == userPo.getEmail()) {
            userPo.setEmail(BLANK_STRING);
        }
        return userPo;
    }
}