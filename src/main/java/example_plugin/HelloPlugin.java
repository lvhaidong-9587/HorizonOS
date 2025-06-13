package example_plugin;

import code.Plugin;
import code.PluginContext;

/**
 * Auth lhd
 * Date 2025/6/12 11:42
 * Annotate
 {
 "id": "hello-plugin",
 "name": "Hello 插件",
 "version": "1.0.0",
 "mainClass": "HelloPlugin",
 "jar": "plugin.jar"
 }
 */
public class HelloPlugin implements Plugin {
    @Override
    public void start(PluginContext context) {
        context.log("Hello Plugin 启动");
        context.reportStat("启动时间", System.currentTimeMillis());
        context.setAttr("startFlag", true);
        new Thread(() -> {
            while (Boolean.TRUE.equals(context.getAttr("startFlag"))) {
                context.log("Hello Plugin 正在运行...");
                context.reportStat("执行次数", 1);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
            }
        }).start();
    }

    @Override
    public void stop() {
        System.out.println("Hello Plugin 停止");
    }
}
