package com.code;

import lombok.Data;

/**
 * Auth lhd
 * Date 2025/6/12 11:43
 * Annotate
 */
@Data
public class PluginMetadata {

    private String id;
    private String name;
    private String version;
    private String mainClass;
    private String jar;
}
