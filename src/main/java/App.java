import code.PluginManager;

/**
 * Auth lhd
 * Date 2025/6/12 11:40
 * Annotate
 */
public class App {

    public static void main(String[] args) throws Exception {
        PluginManager pluginManager = new PluginManager();
        pluginManager.loadAll("plugins");

        System.out.println("当前插件:");
        pluginManager.list().forEach(System.out::println);

        // 简单运行 10 秒后卸载插件
        Thread.sleep(10000);
        pluginManager.stop("hello-plugin");
        System.out.println("插件 hello-plugin 已停止");
    }
}
