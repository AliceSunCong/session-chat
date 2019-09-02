package com.sun.sessionchat.controller;

import com.sun.sessionchat.util.R;
import com.sun.sessionchat.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author SunCongCong
 * @date 2019/8/29 11:13
 * @intro
 */
@Slf4j
@Controller
public class RouterController {
    /**
     * 登陆封面
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "login";
    }

    /**
     * 登陆接口
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request){
        Enumeration<String> ids = request.getSession().getAttributeNames();
        while (ids.hasMoreElements()){
            String id = ids.nextElement();
            if (request.getSession().getAttribute(id) instanceof User){
                if (((User)request.getSession().getAttribute(id)).getName().equals(user.getName())){

                }
            }
        }
        request.getSession().setAttribute(user.getId().toString(),user);
        log.info("session >> {}",request.getSession().getAttribute(user.getId().toString()));
        return new R();
    }

    /**
     * 首页入口
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/{id}/chat")
    public String index(Long id,HttpServletRequest request){
        Object user = request.getSession().getAttribute(id.toString());
        if (user == null){
            return "redirect:/";
        }
        return "index";
    }
}
