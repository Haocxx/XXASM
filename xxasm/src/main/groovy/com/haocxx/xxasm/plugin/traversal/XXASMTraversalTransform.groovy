package com.haocxx.xxasm.plugin.traversal

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.api.transform.TransformOutputProvider
import com.android.build.gradle.internal.pipeline.TransformManager

/**
 * Created by Haocxx
 * on 2019-09-12
 */
class XXASMTraversalTransform extends Transform {

    @Override
    String getName() {
        return "XXASMTraversalTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) {
        println("============ do XXASMTraversalTransform transform start ============")
        def startTime = System.currentTimeMillis()

        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        // clear last output
        if (outputProvider != null)
            outputProvider.deleteAll()

        //traversal inputs
        inputs.each { TransformInput input ->
            //traversal directoryInputs
            input.directoryInputs.each { DirectoryInput directoryInput ->
                //handle directoryInputs
                XXASMTraversalTransformHandler.handleDirectoryInput(directoryInput, outputProvider)
            }

            //traversal jarInputs
            input.jarInputs.each { JarInput jarInput ->
                //handle jarInputs
                XXASMTraversalTransformHandler.handleJarInputs(jarInput, outputProvider)
            }
        }

        def cost = (System.currentTimeMillis() - startTime) / 1000
        println("============ do XXASMTraversalTransform transform end ============")
        println "XXASMTraversalTransform transform cost ： $cost s"

        super.transform(transformInvocation)
    }
}
