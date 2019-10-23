package com.haocxx.xxasm.plugin.manager

import com.haocxx.xxasm.plugin.XXASMConfig

/**
 * Created by Haocxx
 * on 2019-10-11
 */
class BuildPropertyManager {
    private static sInstance
    private String mBuildDir
    private XXASMConfig mConfig

    static void init() {
        sInstance = new BuildPropertyManager()
    }

    static BuildPropertyManager getInstance() {
        return sInstance
    }

    void setBuildDir(String dir) {
        mBuildDir = dir
    }

    String getBuildDir() {
        return mBuildDir
    }

    void setConfig(XXASMConfig config) {
        mConfig = config
        println("BuildPropertyManager: getIgnoreListPath : " + getIgnoreListPath())
        println("BuildPropertyManager: isRemoveNonStaticPrivateMethodSignEnable : " + isRemoveNonStaticPrivateMethodSignEnable())
        println("BuildPropertyManager: isRemoveNonStaticPrivateFieldSignEnable : " + isRemoveNonStaticPrivateFieldSignEnable())
    }

    String getIgnoreListPath() {
        return mConfig != null ? mConfig.ignoreListPath : ""
    }

    boolean isRemoveNonStaticPrivateMethodSignEnable() {
        return mConfig != null ? mConfig.enableRemoveNonStaticPrivateMethodSign : true
    }

    boolean isRemoveNonStaticPrivateFieldSignEnable() {
        return mConfig != null ? mConfig.enableRemoveNonStaticPrivateFieldSign : true
    }
}