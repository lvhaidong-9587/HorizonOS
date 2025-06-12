package code;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
public class PluginManager {

    private final Map<String, LoadedPlugin> plugins = new ConcurrentHashMap<>();
    private final PluginLoader loader = new PluginLoader();

    public void loadAll(String dirPath) {
        for (File pluginDir : new File(dirPath).listFiles(File::isDirectory)) {
            try {
                LoadedPlugin p = loader.load(pluginDir);
                p.getPlugin().start(new PluginContext(p.getMetadata().getId()));
                plugins.put(p.getMetadata().getId(), p);
            } catch (Exception e) {
                System.err.println("加载插件失败: " + pluginDir + ", 错误: " + e.getMessage());
            }
        }
    }

    public void reload(String pluginId) throws Exception {
        File dir = new File("plugins/" + pluginId);
        stop(pluginId);
        LoadedPlugin p = loader.load(dir);
        p.getPlugin().start(new PluginContext(p.getMetadata().getId()));
        plugins.put(pluginId, p);
    }

    public void stop(String pluginId) {
        LoadedPlugin p = plugins.remove(pluginId);
        if (p != null) p.getPlugin().stop();
    }

    public Set<String> list() {
        return plugins.keySet();
    }
}
