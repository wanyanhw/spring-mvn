package com.self.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转化工具
 */
public class DateTransfUtils {

    /**
     * 将Date类型日期转化为String类型，精确到秒
     * @return dateString 转化后的日期
     */

    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateString = dateFormat.format(date);
        return dateString;
    }
}
