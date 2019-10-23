package com.haocxx.xxasm.plugin.manager

/**
 * Created by Haocxx
 * on 2019-10-11
 */
class LogPrintManager {
    final static String SUB_PATH = "/intermediates/xxasm"
    private static sInstance
    private String mBuildDir

    LogPrinter removedSyntheticAccessMethodLogPrinter
    LogPrinter removedSyntheticAccessFieldLogPrinter
    LogPrinter removePrivateMethodSignLogPrinter
    LogPrinter removeFinalMethodSignLogPrinter
    LogPrinter replaceProtectedMethodSignLogPrinter

    LogPrinter removePrivateFieldSignLogPrinter
    LogPrinter removeFinalFieldSignLogPrinter
    LogPrinter replaceProtectedFieldSignLogPrinter

    static void init() {
        sInstance = new LogPrintManager()
    }

    static LogPrintManager getInstance() {
        return sInstance
    }

    LogPrintManager() {
        setBuildDir(BuildPropertyManager.getInstance().getBuildDir())
        removedSyntheticAccessMethodLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemovedSyntheticAccessMethod.txt")
        removedSyntheticAccessFieldLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemovedSyntheticAccessFieldLog.txt")
        removePrivateMethodSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemovePrivateMethodSign.txt")
        removeFinalMethodSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemoveFinalMethodSign.txt")
        replaceProtectedMethodSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/ReplaceProtectedMethodSign.txt")

        removePrivateFieldSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemovePrivateSignField.txt")
        removeFinalFieldSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/RemoveFinalSignField.txt")
        replaceProtectedFieldSignLogPrinter = new LogPrinter(mBuildDir + SUB_PATH + "/ReplaceProtectedSignField.txt")
    }

    void setBuildDir(String dir) {
        mBuildDir = dir
    }

    static class LogPrinter {
        String logFilePath

        LogPrinter(String logFilePath) {
            this.logFilePath = logFilePath
            File file = new File(logFilePath)
            if (file.exists()) {
                file.delete()
            }
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs()
            }
            if (!file.createNewFile()) {
                println("XXASM LogPrinter: create file failed. " + logFilePath)
            }
        }

        synchronized void printLog(String line) {
            try {
                FileWriter fw = new FileWriter(new File(logFilePath), true)
                BufferedWriter bw = new BufferedWriter(fw)
                bw.write(line + "\n")
                bw.close()
                fw.close()
            } catch (Exception e) {
            }
        }
    }
}