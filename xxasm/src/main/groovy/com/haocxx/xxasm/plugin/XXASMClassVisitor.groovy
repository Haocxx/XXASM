package com.haocxx.xxasm.plugin

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
        MethodVisitor result
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
        }
        if ((Opcodes.ACC_PRIVATE & access) == Opcodes.ACC_PRIVATE) {
            if (XXASMTraversalManager._instance.sPrivateMethodSet.contains(new XXASMTraversalManager.MethodInfo(mClassName, name))) {
                access -= Opcodes.ACC_PRIVATE
            }
        }
        if ((Opcodes.ACC_SYNTHETIC & access) == Opcodes.ACC_SYNTHETIC) {
            if (XXASMTraversalManager._instance.sPrivateAccessMethodMap.get(new XXASMTraversalManager.MethodInfo(mClassName, name)) != null) {
                return null
            }
        }
        result = super.visitMethod(access, name, desc, signature, exceptions)
        return result == null ? null : new XXASMMethodVisitor(result)
    }

    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
        }
        if ((Opcodes.ACC_PRIVATE & access) == Opcodes.ACC_PRIVATE) {
            access -= Opcodes.ACC_PRIVATE
        }
        return super.visitField(access, name, desc, signature, value)
    }
}
