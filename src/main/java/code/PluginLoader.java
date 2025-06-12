package code;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
public class PluginLoader {

    public LoadedPlugin load(File pluginDir) throws Exception {
        File jsonFile = new File(pluginDir, "plugin.json");
        PluginMetadata meta = new Gson().fromJson(new FileReader(jsonFile), PluginMetadata.class);

        URL[] urls = { new File(pluginDir, meta.getJar()).toURI().toURL() };
        URLClassLoader loader = new URLClassLoader(urls, getClass().getClassLoader());

        Class<?> clazz = loader.loadClass(meta.getMainClass());
        Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();

        return new LoadedPlugin(meta, plugin, loader);
    }
}
