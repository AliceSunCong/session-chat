package com.sun.sessionchat.util;

import com.sun.sessionchat.entity.Message;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author SunCongCong
 * @date 2019/8/29 14:24
 * @intro
 */
public class CoreUtil {
    /**
     * 按照时间顺序向list中push数据
     * @param list
     */
    public static void push(List<Message> list){
        list.sort(Comparator.comparing(Message::getTime));
    }
    /**
     * format date
     * @param date
     * @return
     */
    public static String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
