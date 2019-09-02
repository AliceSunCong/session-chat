package com.sun.sessionchat.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SunCongCong
 * @date 2019/8/29 11:17
 * @intro 在线用户类
 */
@Data
public class User implements Serializable {
    //id
    private Long id;
    //姓名
    private String name;
    //头像
    private String avatar;

    public void setName(String name){
        this.name = name.trim();
    }
}
