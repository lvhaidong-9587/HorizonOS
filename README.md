# HorizonOS 程序操作和说明

## 程序功能概述

HorizonOS 是一个基于插件架构的系统，支持动态加载、管理和监控插件。通过插件机制，用户可以扩展系统的功能，同时系统提供了对插件状态的实时监控和日志记录功能。


## 依赖的关键库和工具
       <dependency>
            <groupId>org.github</groupId>
            <artifactId>horizon</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

## 配置仓库
 <!-- 配置私有github仓库 -->
        <repository>
            <id>github</id>
            <url>https://maven.pkg.github.com/lvhaidong-9587/horizon</url>
        </repository>
## 程序运行操作步骤

1. 确保已安装 JDK 17 或更高版本。
2. 克隆项目代码并使用 Maven 构建项目：
   ```bash
   mvn clean install
   ```
3. 在 `plugins` 目录下放置插件文件夹，每个插件文件夹需包含 `plugin.json` 和 `plugin.jar` 文件。
4. 运行程序：
   ```bash
   java -jar target/HorizonOS-1.0-SNAPSHOT.jar
   ```
5. 程序启动后会自动加载插件，并在控制台输出插件状态信息。

## 注意事项

- 确保 `plugins` 目录存在且结构正确，否则插件无法加载。
- 如果插件加载失败，请检查日志以获取详细错误信息。
- 插件的 `plugin.json` 文件需符合预期格式，否则可能导致加载失败。
