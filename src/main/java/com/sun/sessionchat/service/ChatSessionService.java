package com.sun.sessionchat.service;

import com.sun.sessionchat.entity.Message;
import com.sun.sessionchat.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author SunCongCong
 * @date 2019/8/29 13:44
 * @intro
 */
public interface ChatSessionService {
    /**
     * 推送消息，存储到session中
     * @param fromId
     * @param message
     * @param session
     */
    void pushMessage(String fromId,String toId, String message, HttpSession session);

    /**
     * 获取在线用户列表
     * @param session
     * @return
     */
    List<User> onlineList(HttpSession session);

    /**
     * 获取公共消息内容--群组
     * @param session HttpSession
     * @return
     */
    List<Message> commonList(HttpSession session);

    /**
     * 获取该用户与指定窗口的推送消息
     * @param fromId 推送方ID
     * @param toId 接收方ID
     * @param session HttpSession
     * @return
     */
    List<Message> selfList(String fromId,String toId,HttpSession session);
}
