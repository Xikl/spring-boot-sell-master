package com.ximo.springbootsellmaster.util;

import com.ximo.springbootsellmaster.enums.CodeEnums;

/**
 * Created by 朱文赵
 * 2017/9/22
 */
public class EnumUtil {

    public static <T extends CodeEnums> T getByCode(Integer code, Class<T> enumCLass){
        for (T each : enumCLass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }


}
