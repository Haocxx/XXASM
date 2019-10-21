package com.haocxx.xxasm.plugin

import com.haocxx.xxasm.plugin.manager.IgnoreManager
import com.haocxx.xxasm.plugin.manager.LogPrintManager
import com.haocxx.xxasm.plugin.traversal.XXASMTraversalManager
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created by Haocxx
 * on 2019-09-12
 */
class XXASMClassVisitor extends ClassVisitor {

    private String mClassName

    XXASMClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv)
    }

    @Override
    void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        // save current visit class name
        this.mClassName = name

        if (IgnoreManager.getInstance().isIgnoreClass(name)) {
            super.visit(version, access, name, signature, superName, interfaces)
            return
        }

        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
        }
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (IgnoreManager.getInstance().isIgnoreClass(mClassName)) {
            return super.visitMethod(access, name, desc, signature, exceptions)
        }

        MethodVisitor result
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            LogPrintManager.getInstance().removeFinalMethodSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
            access -= Opcodes.ACC_FINAL
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            LogPrintManager.getInstance().replaceProtectedMethodSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
        }
        if ((Opcodes.ACC_PRIVATE & access) == Opcodes.ACC_PRIVATE) {
            if (XXASMTraversalManager._instance.sPrivateMethodSet.contains(new XXASMTraversalManager.MethodInfo(mClassName, name))) {
                LogPrintManager.getInstance().removePrivateMethodSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
                access -= Opcodes.ACC_PRIVATE
            }
        }
        if ((Opcodes.ACC_SYNTHETIC & access) == Opcodes.ACC_SYNTHETIC) {
            XXASMTraversalManager.MethodInfo methodInfo = XXASMTraversalManager._instance.sPrivateAccessMethodMap.get(new XXASMTraversalManager.MethodInfo(mClassName, name))
            if (methodInfo != null && XXASMTraversalManager._instance.sPrivateMethodSet.contains(methodInfo)) {
                LogPrintManager.getInstance().removedSyntheticAccessMethodLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
                return null
            }
        }
        result = super.visitMethod(access, name, desc, signature, exceptions)
        return result == null ? null : new XXASMMethodVisitor(result)
    }

    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (IgnoreManager.getInstance().isIgnoreClass(mClassName)) {
            return super.visitField(access, name, desc, signature, value)
        }

        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            LogPrintManager.getInstance().removeFinalFieldSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
            access -= Opcodes.ACC_FINAL
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            LogPrintManager.getInstance().replaceProtectedFieldSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
        }
        if ((Opcodes.ACC_PRIVATE & access) == Opcodes.ACC_PRIVATE) {
            LogPrintManager.getInstance().removePrivateFieldSignLogPrinter.printLog(mClassName.replace('/', '.') + "::" + name)
            access -= Opcodes.ACC_PRIVATE
        }
        return super.visitField(access, name, desc, signature, value)
    }
}
