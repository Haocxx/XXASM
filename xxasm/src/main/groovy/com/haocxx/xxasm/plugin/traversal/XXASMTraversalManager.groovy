package com.haocxx.xxasm.plugin.traversal

enum XXASMTraversalManager {
    _instance

    HashSet<MethodInfo> sPrivateMethodSet = new HashSet<>()
    HashSet<MethodInfo> sSyntheticMethodSet = new HashSet<>()

    static class MethodInfo {
        String className
        String methodName

        MethodInfo(String className, String methodName) {
            this.className = className
            this.methodName = methodName
        }

        @Override
        boolean equals(Object o) {
            if (o == null) {
                return false
            }
            if (!o instanceof MethodInfo) {
                return false
            }
            return (className == ((MethodInfo)o).className) && (methodName == ((MethodInfo)o).methodName)
        }

        @Override
        String toString() {
            return "MethodInfo: " + className + " " + methodName
        }

        @Override
        int hashCode() {
            return className.hashCode() + methodName.hashCode()
        }
    }
}