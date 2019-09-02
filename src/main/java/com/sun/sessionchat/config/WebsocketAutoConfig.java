package com.sun.sessionchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author SunCongCong
 * @date 2019/8/30 9:12
 * @intro websocket 自动配置类
 */
@Configuration
public class WebsocketAutoConfig {
    @Bean
    public ServerEndpointExporter endpointExporter(){
        return new ServerEndpointExporter();
    }
}
