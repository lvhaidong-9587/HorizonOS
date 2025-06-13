package com.plugins_demo;

import api.Plugin;
import api.PluginContext;

/**
 * Auth lhd
 * Date 2025/6/13 15:47
 * Annotate
 * json文件如下
 {
 "id": "hello-plugin",
 "name": "Hello 插件",
 "version": "1.0.0",
 "mainClass": "plugins_demo.HelloPlugin",
 "jar": "plugin.jar"
 }
 */
public class HelloPlugin implements Plugin {

    @Override
    public void start(PluginContext context) {
        context.log("HelloPlugin 启动");
        context.setAttr("startTime", System.currentTimeMillis());
    }

    @Override
    public void stop() {
        System.out.println("HelloPlugin 停止");
    }
}

