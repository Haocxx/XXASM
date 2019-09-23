package com.haocxx.xxasm.plugin

import com.android.build.gradle.AppExtension
import com.haocxx.xxasm.plugin.traversal.XXASMTraversalTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Haocxx
 * on 2019-09-12
 */
class XXASMPlugin implements Plugin<Project> {
    void apply(Project project) {
        println("============ do XXASMPlugin apply ============")

        //registerTransform
        def android = project.extensions.getByType(AppExtension)
        android.registerTransform(new XXASMTraversalTransform())
        android.registerTransform(new XXASMTransform())
    }
}
