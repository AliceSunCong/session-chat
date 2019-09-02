package com.sun.sessionchat.service.impl;

import com.sun.sessionchat.constant.CommonConstant;
import com.sun.sessionchat.entity.Message;
import com.sun.sessionchat.entity.User;
import com.sun.sessionchat.service.ChatSessionService;
import com.sun.sessionchat.util.CoreUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author SunCongCong
 * @date 2019/8/29 14:20
 * @intro
 */
@Slf4j
@Service
public class ChatSessionServiceImpl implements ChatSessionService {
    @Override
    public void pushMessage(String fromId, String toId, String message, HttpSession session) {
        Object user = session.getAttribute(fromId);
        if (user instanceof User) {
            Message entity = new Message();
            entity.setMessage(message);
            entity.setFrom((User) user);
            entity.setTime(CoreUtil.format(new Date()));
            if (toId != null) {
                //查询接收方消息
                Object to = session.getAttribute(toId);
                if (to instanceof User) {
                    entity.setTo((User) to);
                }
                //单个用户推送
                push(entity, CommonConstant.CHAT_FROM_PREFIX+fromId+CommonConstant.CHAT_TO_PREFIX+toId,session);
            } else {
                //公共消息--群组
                entity.setTo(null);
                push(entity,CommonConstant.CHAT_COMMON_PREFIX+fromId,session);
            }
        }
    }

    private void push(Message entity, String key, HttpSession session) {
        //这里按照 PREFIX_ID 格式，作为KEY存储消息记录
        //但一个用户可能推送很多消息，VALUE应该是数组
        List<Message> list = new ArrayList<>();
        Object msg = session.getAttribute(key);
        if (msg==null){
            //第一次推送消息
            list.add(entity);
        }else {
            //第n次推送消息
            if (msg instanceof List){
                list = (List<Message>)msg;
                list.add(entity);
            }
        }
        session.setAttribute(key,list);
    }

    @Override
    public List<User> onlineList(HttpSession session) {
        List<User> list = new ArrayList<>();
        Enumeration<String> ids = session.getAttributeNames();
        while (ids.hasMoreElements()){
            String id = ids.nextElement();
            if (session.getAttribute(id) instanceof User){
                list.add((User)session.getAttribute(id));
            }
        }
        return list;
    }

    @Override
    public List<Message> commonList(HttpSession session) {
        List<Message> list = new ArrayList<>();
        Enumeration<String> ids = session.getAttributeNames();
        while (ids.hasMoreElements()){
            String id = ids.nextElement();
            if (id.startsWith(CommonConstant.CHAT_COMMON_PREFIX)){
                Object attribute = session.getAttribute(id);
                if (attribute instanceof List){
                    List<Message> data = (List<Message>) attribute;
                    list.addAll(data);
                    //按照时间顺序放置消息
                    CoreUtil.push(list);
                }
            }
        }
        return list;
    }

    @Override
    public List<Message> selfList(String fromId, String toId, HttpSession session) {
        List<Message> list = new ArrayList<>();
        Enumeration<String> ids = session.getAttributeNames();
        while (ids.hasMoreElements()){
            String id = ids.nextElement();
            //
            if (id.startsWith(CommonConstant.CHAT_FROM_PREFIX)||id.indexOf(CommonConstant.CHAT_TO_PREFIX)>0){
                Object attribute = session.getAttribute(id);
                if (attribute instanceof List){
                    list = (List<Message>) attribute;
                }
            }
        }
        return list;
    }
}
