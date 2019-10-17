package cn.blocks.commonutils.utils;

import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

/**
 * @description
 *          时间操作工具
 *
 * @author Somnus丶y
 * @date 2019/8/28 10:49
 */
public class TimeUtils {

    /**
     * @description
     *          format
     *
     * @param pattern, time
     * @return java.lang.String
     * @throws
     * @author Somnus丶y
     * @date 2019/10/16
     */
    public static String convertTime(String pattern,Long time){
        if(Objects.isNull(time)){
            return "";
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }



    /**
     * @description
     *          parse time
     *
     * @param pattern, text
     * @return java.lang.Long
     * @throws
     * @author Somnus丶y
     * @date 2019/10/16
     */
    public static Long parseTime(String pattern,String text){
        if(StringUtils.isEmpty(text)){
            return null;
        }
        TemporalAccessor parse = DateTimeFormatter.ofPattern(pattern).parse(text);
        LocalDateTime from = LocalDateTime.from(parse);
        return Timestamp.valueOf(from).getTime();
    }


}
