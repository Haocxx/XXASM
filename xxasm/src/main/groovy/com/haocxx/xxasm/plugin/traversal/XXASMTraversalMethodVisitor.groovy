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
        println("XXASMTraversalMethodVisitor visitMethodInsn: " + owner + " " + name + " " + desc)
        super.visitMethodInsn(opcode, owner, name, desc, itf)
        XXASMTraversalManager._instance.sSyntheticMethodSet.add(new XXASMTraversalManager.MethodInfo(className, methodName))
    }

    @Override
    void visitFieldInsn(int opcode, String owner, String name, String desc) {
        super.visitFieldInsn(opcode, owner, name, desc)
        println("XXASMTraversalMethodVisitor visitFieldInsn: " + opcode + owner + name)
    }
}