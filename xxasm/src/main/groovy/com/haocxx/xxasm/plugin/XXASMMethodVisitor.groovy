package com.haocxx.xxasm.plugin

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
//        if ("android/util/Log" == owner && name == "d") {
//            println("XXASMMethodVisitor visitMethodInsn: remove " + owner + " " + name + " " + desc)
//        }
        super.visitMethodInsn(opcode, owner, name, desc, itf)
    }
}