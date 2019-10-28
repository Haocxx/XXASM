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
            XXASMTraversalManager.MethodInfo key = new XXASMTraversalManager.MethodInfo(owner, name, desc)
            XXASMTraversalManager.MethodInfo value = XXASMTraversalManager._instance.sPrivateAccessMethodMap.get(key)
            if (value != null && XXASMTraversalManager._instance.sPrivateMethodSet.contains(value)) {
                int blockerPos = 0
                for (int i = 0; i < desc.length(); i++) {
                    if (desc[i] == ';') {
                        blockerPos = i
                        break
                    }
                }
                StringBuffer descTrunk = new StringBuffer("(")
                for (int i = blockerPos + 1; i < desc.length(); i++) {
                    descTrunk.append(desc[i])
                }
                desc = descTrunk.toString()
                super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, value.className, value.methodName, desc, itf)
                return
            }

            XXASMTraversalManager.FieldInfo fieldInfo = XXASMTraversalManager._instance.sPrivateAccessFieldMap.get(key)
            if (fieldInfo != null && XXASMTraversalManager._instance.sPrivateFieldSet.contains(fieldInfo)) {
                //FIXME:
                //visitVarInsn(Opcodes.ALOAD, 0)
                int breakIndex = 0
                for (int i = 0; i < desc.length(); i++) {
                    if (desc[i] == ')') {
                        breakIndex = i
                        break
                    }
                }
                desc = desc.substring(breakIndex + 1, desc.length())
                visitFieldInsn(Opcodes.GETFIELD, fieldInfo.className, fieldInfo.fieldName, desc)
                return
            }
        }
        if (XXASMTraversalManager._instance.sPrivateMethodSet.contains(new XXASMTraversalManager.MethodInfo(owner, name, desc))) {
            if (opcode == Opcodes.INVOKESPECIAL) {
                opcode = Opcodes.INVOKEVIRTUAL
            }
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf)
    }
}