package com.haocxx.xxasm.plugin.traversal

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created by Haocxx
 * on 2019-09-16
 */
class XXASMTraversalMethodVisitor extends MethodVisitor {
    String className
    String methodName

    XXASMTraversalMethodVisitor(String className, String methodName, MethodVisitor mv) {
        super(Opcodes.ASM5, mv)
        this.className = className
        this.methodName = methodName
    }

    @Override
    void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (opcode == Opcodes.INVOKESPECIAL) {
            XXASMTraversalManager.MethodInfo pre = new XXASMTraversalManager.MethodInfo(className, methodName)
            XXASMTraversalManager.MethodInfo after = new XXASMTraversalManager.MethodInfo(owner, name)
            XXASMTraversalManager._instance.sPrivateAccessMethodMap.put(pre, after)
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf)
    }

    @Override
    void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (opcode == Opcodes.GETFIELD) {
            XXASMTraversalManager.MethodInfo pre = new XXASMTraversalManager.MethodInfo(className, methodName)
            XXASMTraversalManager.FieldInfo after = new XXASMTraversalManager.FieldInfo(owner, name)
            XXASMTraversalManager._instance.sPrivateAccessFieldMap.put(pre, after)
        }
        super.visitFieldInsn(opcode, owner, name, desc)
    }
}