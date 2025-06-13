package com.impl;

import api.PluginContext;
import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Auth lhd
 * Date 2025/6/13 15:43
 * Annotate
 */
@Slf4j
public class PluginContextImpl implements PluginContext {

    private final String pluginId;
    private final Map<String, Object> attrMap = new ConcurrentHashMap<>();

    public PluginContextImpl(String pluginId) {
        this.pluginId = pluginId;
    }

    @Override
    public void log(String message) {
        log.info("[{}] {}", pluginId, message);
    }

    @Override
    public void reportStat(String key, Object value) {
        // 简单示例，打印统计
        log.info("[{}] STAT: {} = {}", pluginId,key,value);
    }

    @Override
    public void setAttr(String key, Object value) {
        attrMap.put(key, value);
    }

    @Override
    public Object getAttr(String key) {
        return attrMap.get(key);
    }
}

