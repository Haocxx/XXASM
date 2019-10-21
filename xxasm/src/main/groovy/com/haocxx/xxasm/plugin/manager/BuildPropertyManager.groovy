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
    }

    String getIgnoreListPath() {
        return mConfig != null ? mConfig.ignoreListPath : ""
    }
}