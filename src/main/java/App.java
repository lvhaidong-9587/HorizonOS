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
    }
}
