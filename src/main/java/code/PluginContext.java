package code;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
public class PluginContext {

    private final String pluginId;
    private final Map<String, Object> attributes = new ConcurrentHashMap<>();

    public PluginContext(String pluginId) {
        this.pluginId = pluginId;
    }

    public void log(String msg) {
        System.out.println("[插件 " + pluginId + "] " + msg);
    }

    public void reportStat(String key, long value) {
        PluginMonitorService.record(pluginId, key, value);
    }

    public void setAttr(String key, Object val) {
        attributes.put(key, val);
    }

    public Object getAttr(String key) {
        return attributes.get(key);
    }
}
