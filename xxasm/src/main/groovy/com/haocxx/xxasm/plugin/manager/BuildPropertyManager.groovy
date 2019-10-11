package com.haocxx.xxasm.plugin.manager

/**
 * Created by Haocxx
 * on 2019-10-11
 */
class BuildPropertyManager {
    private static sInstance
    private String mBuildDir

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
}