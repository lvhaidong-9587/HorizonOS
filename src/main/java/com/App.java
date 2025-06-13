package com;

import com.code.PluginManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Auth lhd
 * Date 2025/6/12 11:40
 * Annotate
 */
@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        PluginManager pluginManager = new PluginManager();
        pluginManager.loadAll("plugins");
        log.info("当前插件: ");
        pluginManager.list().forEach(log::info);

        // 简单运行 10 秒后卸载插件
        Thread.sleep(10000);
        pluginManager.stop("hello-plugin");
        log.info("插件 hello-plugin 已停止");
    }
}
