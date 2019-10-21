package com.haocxx.xxasm.plugin.util

/**
 * Created by Haocxx
 * on 2019-10-18
 */
class StringUtil {
    static boolean isEmpty(String s) {
        if (s == null) {
            return true
        }
        s.replace(" ", "")
        if (s == "") {
            return true
        }
        return false
    }

    static String formatClassName(String name) {
        if (!isEmpty(name)) {
            name = name.replace("/", ".")
        }
        return name
    }
}