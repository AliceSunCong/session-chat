package com.sun.sessionchat.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author SunCongCong
 * @date 2019/8/29 10:43
 * @intro request 请求监听类
 */
@Component
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ((HttpServletRequest)sre.getServletRequest()).getSession();
    }
}
