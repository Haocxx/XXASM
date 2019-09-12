package com.haocxx.xxasm.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class XXASMPlugin implements Plugin<Project> {
    void apply(Project project) {
        println("========================");
        println("hello gradle plugin!");
        println("========================");
    }
}
