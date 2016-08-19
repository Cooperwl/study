package study.cooper.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 * <p/>
 * 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 *
 * @author WangLiang
 * @version1.0
 */

public final class DateUtils {

    /**
     *
     * 英文全称 如：2010-12-01 23:15
     */

    public static String FORMAT_DATE = "yyyy-MM-dd HH:mm";

    /**
     * 计算两个日期时间相差几天,几小时
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param format    格式
     * @return
     */
    public static String dateDiff(String startTime, String endTime, String format) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        //获得两个时间的毫秒时间差异
        long diff = 0;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        StringBuilder diffs = new StringBuilder();

        if (day != 0){
            diffs.append(day+"天");
        }
        if (hour != 0){
            diffs.append(hour+"小时");
        }
        if (min != 0){
            diffs.append(min+"分钟");
        }
        return diffs.toString();

    }


    public static void main(String[] args) {
        String result = dateDiff("2015-01-01 11:00:50", "2015-01-01 12:03:00", FORMAT_DATE);
        System.out.println(result);
    }

}
