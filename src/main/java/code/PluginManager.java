package code;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
public class PluginManager {

    private final Map<String, LoadedPlugin> plugins = new ConcurrentHashMap<>();
    private final PluginLoader loader = new PluginLoader();

    public void loadAll(String dirPath) {
        File baseDir = new File(dirPath);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.err.println("插件目录不存在或不是目录: " + baseDir.getAbsolutePath());
            return;
        }

        File[] pluginDirs = baseDir.listFiles(File::isDirectory);
        if (pluginDirs == null || pluginDirs.length == 0) {
            System.err.println("插件目录下无插件子目录: " + baseDir.getAbsolutePath());
            return;
        }

        for (File pluginDir : pluginDirs) {
            File jsonFile = new File(pluginDir, "plugin.json");
            File jarFile = new File(pluginDir, "plugin.jar");

            if (!jsonFile.exists() || !jarFile.exists()) {
                System.err.println("插件缺少必要文件: " + pluginDir.getName());
                continue;
            }

            try {
                LoadedPlugin plugin = loader.load(pluginDir);
                plugin.getPlugin().start(new PluginContext(plugin.getMetadata().getId()));
                plugins.put(plugin.getMetadata().getId(), plugin);
                System.out.println("插件已加载: " + plugin.getMetadata().getName());
            } catch (Exception e) {
                System.err.println("插件加载失败: " + pluginDir.getName() + "，错误：" + e.getMessage());
                e.printStackTrace();
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
