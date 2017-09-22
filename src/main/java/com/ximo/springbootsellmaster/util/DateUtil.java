package com.ximo.springbootsellmaster.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * Created by 朱文赵
 * 2017/9/19
 */
public class DateUtil {

    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化时间
     * @param time
     * @param timeFormat
     * @return
     */
    public static String parseDateToString(Date time, String timeFormat){
        return new SimpleDateFormat(timeFormat).format(time);
    }

    public static String parseDateToString(Date time){
        return parseDateToString(time, DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
    }




}
