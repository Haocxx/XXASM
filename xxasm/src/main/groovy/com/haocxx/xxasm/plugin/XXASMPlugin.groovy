package com.haocxx.xxasm.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Haocxx
 * on 2019-09-12
 */
class XXASMPlugin implements Plugin<Project> {
    void apply(Project project) {
        println("========================")
        println("XXASMPlugin start")
        println("========================")

        //registerTransform
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new XXASMTransform())
    }
}
