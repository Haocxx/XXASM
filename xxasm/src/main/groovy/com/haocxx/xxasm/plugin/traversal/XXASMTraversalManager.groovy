package com.haocxx.xxasm.plugin.traversal

/**
 * Created by Haocxx
 * on 2019-9-23
 */
enum XXASMTraversalManager {
    _instance

    HashSet<MethodInfo> sPrivateMethodSet = new HashSet<>()
    HashMap<MethodInfo, MethodInfo> sPrivateAccessMethodMap = new HashMap<>()
    HashMap<MethodInfo, FieldInfo> sPrivateAccessFieldMap = new HashMap<>()

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

    static class FieldInfo {
        String className
        String fieldName

        FieldInfo(String className, String fieldName) {
            this.className = className
            this.fieldName = fieldName
        }

        @Override
        boolean equals(Object o) {
            if (o == null) {
                return false
            }
            if (!o instanceof FieldInfo) {
                return false
            }
            return (className == ((FieldInfo)o).className) && (fieldName == ((FieldInfo)o).fieldName)
        }

        @Override
        String toString() {
            return "FieldInfo: " + className + " " + fieldName
        }

        @Override
        int hashCode() {
            return className.hashCode() + fieldName.hashCode()
        }
    }
}