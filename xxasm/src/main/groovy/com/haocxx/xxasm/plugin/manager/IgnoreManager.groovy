package com.haocxx.xxasm.plugin.manager

import com.haocxx.xxasm.plugin.util.StringUtil

/**
 * Created by Haocxx
 * on 2019-10-18
 */
class IgnoreManager {
    private static sInstance
    private String ignoreListPath
    private List<String> mIgnoreClasses = new ArrayList<>()
    private List<String> mIgnoreClassPrefixes = new ArrayList<>()
    private List<String> mIgnoreClassSubfixes = new ArrayList<>()
    private List<String> mIgnoreClassContains = new ArrayList<>()

    static void init() {
        sInstance = new IgnoreManager()
    }

    static IgnoreManager getInstance() {
        return sInstance
    }

    IgnoreManager() {
        ignoreListPath = BuildPropertyManager.getInstance().getIgnoreListPath()
        if (!StringUtil.isEmpty(ignoreListPath)) {
            loadIgnoreFile()
        }
    }

    private void loadIgnoreFile() {
        String encoding = "GBK"
        File file = new File(ignoreListPath)
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding)
            BufferedReader bufferedReader = new BufferedReader(read)
            String line
            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace(" ", "")
                if (StringUtil.isEmpty(line)) {
                    continue
                }
                line = StringUtil.formatClassName(line)
                if (line.startsWith("**") && line.endsWith("**") && line.length() > 4) {
                    line = line.substring(2, line.length() - 2)
                    mIgnoreClassContains.add(line)
                } else if (line.startsWith("**")) {
                    line = line.substring(2, line.length())
                    mIgnoreClassSubfixes.add(line)
                } else if (line.endsWith("**")) {
                    line = line.substring(0, line.length() - 2)
                    mIgnoreClassPrefixes.add(line)
                } else {
                    mIgnoreClasses.add(line)
                }
            }
            bufferedReader.close()
            read.close()
        }
    }

    /**
     * @param className FORMAT : "com.xx.xx.XXClass" or "com/xx/xx/XXClass"
     * @return
     */
    boolean isIgnoreClass(String className) {
        if (StringUtil.isEmpty(className)) {
            return true
        }
        className = StringUtil.formatClassName(className)
        for (String item : mIgnoreClasses) {
            if (className == item) {
                return true
            }
        }
        for (String item : mIgnoreClassPrefixes) {
            if (className.startsWith(item)) {
                return true
            }
        }
        for (String item : mIgnoreClassSubfixes) {
            if (className.endsWith(item)) {
                return true
            }
        }
        for (String item : mIgnoreClassContains) {
            if (className.contains(item)) {
                return true
            }
        }
        return false
    }
}