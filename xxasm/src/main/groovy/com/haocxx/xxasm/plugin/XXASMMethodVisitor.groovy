package com.haocxx.xxasm.plugin

import com.haocxx.xxasm.plugin.traversal.XXASMTraversalManager
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created by Haocxx
 * on 2019-09-16
 */
class XXASMMethodVisitor extends MethodVisitor {

    XXASMMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv)
    }

    @Override
    void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        //if (owner == "com/haocxx/xxasm/MainActivity") {
        if (XXASMTraversalManager._instance.sPrivateMethodSet.contains(new XXASMTraversalManager.MethodInfo(owner, name))) {
            if (opcode == Opcodes.INVOKESPECIAL && "<init>" != name) {
                opcode = Opcodes.INVOKEVIRTUAL
                println("XXASMMethodVisitor visit private MethodInsn: " + owner + " " + name + " " + opcode)
            }
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf)
    }
}