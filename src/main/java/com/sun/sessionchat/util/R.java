package com.sun.sessionchat.util;

import lombok.Data;

/**
 * @author SunCongCong
 * @date 2019/8/29 11:17
 * @intro
 */
@Data
public class R {
    private int code = 200;

    private String msg = "success";

    private Object data;

    public R() {
        super();
    }

    public R(Object data) {
        super();
        this.data = data;
    }

    public R(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
