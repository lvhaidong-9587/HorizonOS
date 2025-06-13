package com.code;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auth lhd
 * Date 2025/6/12 11:41
 * Annotate
 */
public class PluginMonitorService {

    private static final Map<String, Map<String, Long>> stats = new ConcurrentHashMap<>();

    public static void record(String pluginId, String key, long value) {
        stats.computeIfAbsent(pluginId, k -> new ConcurrentHashMap<>())
                .merge(key, value, Long::sum);
    }

    public static Map<String, Long> getStats(String pluginId) {
        return stats.getOrDefault(pluginId, Collections.emptyMap());
    }
}
