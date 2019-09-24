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
        if (opcode == Opcodes.INVOKESTATIC && name.startsWith("access\$")) {
            XXASMTraversalManager.MethodInfo key = new XXASMTraversalManager.MethodInfo(owner, name)
            XXASMTraversalManager.MethodInfo value = XXASMTraversalManager._instance.sPrivateAccessMethodMap.get(key)
            if (value != null) {
                super.visitMethodInsn(Opcodes.INVOKESTATIC, value.className, value.methodName, desc, itf)
                return
            }
        }
        if (XXASMTraversalManager._instance.sPrivateMethodSet.contains(new XXASMTraversalManager.MethodInfo(owner, name))) {
            if (opcode == Opcodes.INVOKESPECIAL) {
                opcode = Opcodes.INVOKEVIRTUAL
            }
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf)
    }

    @Override
    void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (opcode == Opcodes.GETFIELD && name.startsWith("access\$")) {
            XXASMTraversalManager.MethodInfo key = new XXASMTraversalManager.MethodInfo(owner, name)
            XXASMTraversalManager.FieldInfo value = XXASMTraversalManager._instance.sPrivateAccessFieldMap.get(key)
            if (value != null) {
                super.visitFieldInsn(opcode, value.className, value.fieldName, desc)
                return
            }
        }
        super.visitFieldInsn(opcode, owner, name, desc)
    }
}