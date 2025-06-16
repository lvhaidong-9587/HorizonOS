package com.code;

import com.impl.PluginContextImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
@Slf4j
public class PluginManager {

    private final Map<String, LoadedPlugin> plugins = new ConcurrentHashMap<>();
    private final Map<String, String> pluginStatus = new ConcurrentHashMap<>();
    private final PluginLoader loader = new PluginLoader();

    public void loadAll(String dirPath) {
        File baseDir = new File(dirPath);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            log.info("插件目录不存在或不是目录: {}",baseDir.getAbsolutePath());
            return;
        }

        File[] pluginDirs = baseDir.listFiles(File::isDirectory);
        if (pluginDirs == null || pluginDirs.length == 0) {
            log.info("插件目录下无插件子目录: {}",baseDir.getAbsolutePath());
            return;
        }

        for (File pluginDir : pluginDirs) {
            File jsonFile = new File(pluginDir, "plugin.json");
            File jarFile = new File(pluginDir, "plugin.jar");

            if (!jsonFile.exists() || !jarFile.exists()) {
                log.error("插件缺少必要文件: {}",pluginDir.getName());
                continue;
            }

            try {
                LoadedPlugin plugin = loader.load(pluginDir);
                plugin.plugin().start(new PluginContextImpl(plugin.metadata().getId()));
                plugins.put(plugin.metadata().getId(), plugin);
                pluginStatus.put(plugin.metadata().getId(), "RUNNING");
                log.info("插件已加载: {}",plugin.metadata().getName());
            } catch (Exception e) {
                pluginStatus.put(pluginDir.getName(), "FAILED");
                log.error("插件加载失败: {},错误: {}", pluginDir.getName(),e.getMessage(),e);
            }
        }
    }

    public void reload(String pluginId) throws Exception {
        File dir = new File("plugins/" + pluginId);
        stop(pluginId);
        LoadedPlugin p = loader.load(dir);
        p.plugin().start(new PluginContextImpl(p.metadata().getId()));
        plugins.put(pluginId, p);
    }

    public void stop(String pluginId) {
        LoadedPlugin p = plugins.remove(pluginId);
        if (p != null) p.plugin().stop();
    }

    public Set<String> list() {
        return plugins.keySet();
    }

    public void startMonitorThread() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> plugins.forEach((id, plugin) -> {
            String status = pluginStatus.getOrDefault(id, "UNKNOWN");
            log.info("插件: {},状态: {}", id,status);
        }), 0, 5, TimeUnit.SECONDS);
    }

}
