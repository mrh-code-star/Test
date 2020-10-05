package com.hwadee.learn.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 公用函数工具类
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 */

public class Primary {
    // 创建字符串常量
    public final static byte DISABLE = 1;
    public final static byte ENABLE = 0;
    public final static String SPACE = "";

    // 查值参考
    public final static short OP_LEVEL = 100;
    public final static short OP_WORKSTATUS = 200;

    public final static short WN_WORKSTATE = 100;

    public final static short PS_SEX = 100;
    public final static short PS_PERSON_TYPE = 3200;
    public final static short PS_WORKSTATUS = 2300;

    private static final int BARCODE_ID_LEN = 6;

    public final static String ANSI_SQL_DATE_FORMAT = "yyyy-MM-dd";

    public final static String ANSI_SQL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static SimpleDateFormat c_ansiSqlDateTimeFormater = new SimpleDateFormat(
            ANSI_SQL_DATE_TIME_FORMAT);

    private static SimpleDateFormat c_ansiSqlDateFormater = new SimpleDateFormat(
            ANSI_SQL_DATE_FORMAT);

    public final static String RETURN_ERROR = ":erErr";

    public final static String RETURN_OK = ":erOK";

    public final static String DIVISION_SYMBOL = ";;n;;";

    public final static String EQUALS_SYMBOL = "=";

    public final static int INSTITUTION_NUM = 1000;

    public final static short INVALID_WEEK_DATE = 100;

    public final static String NO_DATE = "0000-00-00 ";

    public final static String END_TIME_OF_DAY = " 23:59:59";

    public final static String DATE_MAX = "9999-12-31";

    public final static String DATE_MIN = "0000-01-01";

    public final static int DIALOG_STATE_HIDE = 0;

    public final static int DIALOG_STATE_EXTI = 1;

    /**
     * Format a java.util.Data instance to string as ANSI SQL format("YYYY-MM-DD", see ANSI_SQL_DATE_FORMAT)
     */
    public static String ansiFormatDate(java.util.Date date) {
        return c_ansiSqlDateFormater.format(date);
    }

    /**
     * Parse a datetime string as ANSI-SQL format("YYYY-MM-DD", see ANSI_SQL_DATE_FORMAT)
     *
     * @param dateString a ANSI-SQL format date string
     * @return a java.util.Data instance represdent giving date string
     * @throws java.text.ParseException if input cannot be parsed as ANSI-SQL date format
     */
    public static java.util.Date parseAnsiFormatDate(String dateString) throws java.text.
            ParseException {
        return c_ansiSqlDateFormater.parse(dateString);
    }

    /**
     * Format a java.util.Data instance to string as ANSI-SQL format("YYYY-MM-DD HH:MM:SS", see ANSI_SQL_DATE_TIME_FORMAT)
     */
    public static String ansiFormatDateTime(java.util.Date date) {
        return c_ansiSqlDateTimeFormater.format(date);
    }

    /**
     * Parse a datetime string as ANSI SQL format("YYYY-MM-DD HH:MM:SS", see ANSI_SQL_DATE_TIME_FORMAT)
     *
     * @param datetimeString a ansi sql format date-time string
     * @return a java.util.Data instance represdent giving date-time string
     * @throws java.text.ParseException if input cannot be parsed as ANSI-SQL date-time format
     */
    public static java.util.Date parseAnsiFormatDateTime(String datetimeString) throws java.text.
            ParseException {
        return c_ansiSqlDateTimeFormater.parse(datetimeString);
    }


    /**
     * 构造一个指定长度的字符串
     *
     * @param len     希望返回的字符串的长度
     * @param element 用于组成返回字符串的字符
     * @return 长度为len，全部由element指定的字符组成的字符串
     */
    public static String space(int len, char element) {
        if (len < 1) {
            return "";
        }

        char elements[] = new char[len];
        for (int i = 0; i < len; i++) {
            elements[i] = element;
        }
        return new String(elements);
    }


    //判断两个日期是否是同一天
    public static boolean isTheSameDay(java.util.Date date1, java.util.Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 功能：把系统时间转化为长度为14的字符
     *
     * @return executeTime
     */
    public static String getExecuteTime() {
        String executeTime = "";
        java.sql.Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
        executeTime = executeTime + time;
        //formate timestamp:yyyymmddhhmmss(yyyy:year;mm:month;dd:day;hh:hour;mm:minute;
        //ss:seconds)
        executeTime = executeTime.substring(0, 4) + executeTime.substring(5, 7)
                + executeTime.substring(8, 10) + executeTime.substring(11, 13)
                + executeTime.substring(14, 16) + executeTime.substring(17, 19);
        return executeTime;
    }

}
