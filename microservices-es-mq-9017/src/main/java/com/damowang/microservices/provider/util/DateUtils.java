package com.damowang.microservices.provider.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 * 简单时间工具类
 *
 * @author sculi
 * @create 2020-05-16 1:42
 */
public class DateUtils {
    private static final String DEFAULT_PARTTERN="yyyy-MM-dd";
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    public static Date StringToDate(String date,String ...parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                if(parttern==null){
                    myDate = getDateFormat(DEFAULT_PARTTERN).parse(date);
                }else {
                    myDate = getDateFormat(parttern[0]).parse(date);
                }
            } catch (Exception e) {
            }
        }
        return myDate;
    }

}
