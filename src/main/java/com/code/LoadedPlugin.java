package com.code;


import api.Plugin;

/**
 * Auth lhd
 * Date 2025/6/12 11:44
 * Annotate
 */

public record LoadedPlugin(PluginMetadata metadata, Plugin plugin, ClassLoader loader) {

}
