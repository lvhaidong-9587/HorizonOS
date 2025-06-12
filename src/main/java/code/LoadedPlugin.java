package code;

/**
 * Auth lhd
 * Date 2025/6/12 11:44
 * Annotate
 */
public class LoadedPlugin {

    private final PluginMetadata metadata;
    private final Plugin plugin;
    private final ClassLoader loader;

    public LoadedPlugin(PluginMetadata metadata, Plugin plugin, ClassLoader loader) {
        this.metadata = metadata;
        this.plugin = plugin;
        this.loader = loader;
    }

    public PluginMetadata getMetadata() { return metadata; }
    public Plugin getPlugin() { return plugin; }
    public ClassLoader getLoader() { return loader; }
}
