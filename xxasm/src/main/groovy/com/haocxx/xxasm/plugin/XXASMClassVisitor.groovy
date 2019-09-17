package com.haocxx.xxasm.plugin

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
        println("XXASMClassVisitor : visit -----> started ：" + name)
        // save current visit class name
        this.mClassName = name
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
            println("XXASMClassVisitor : visit final Field :" + name)
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
            println("XXASMClassVisitor : visit private Field ：" + name)
        }
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor result
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
            println("XXASMClassVisitor : visit final Method :" + name)
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
            println("XXASMClassVisitor : visit private Method ：" + name)
        }
        result = super.visitMethod(access, name, desc, signature, exceptions)
        return result == null ? null : new XXASMMethodVisitor(result)
    }

    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if ((Opcodes.ACC_FINAL & access) == Opcodes.ACC_FINAL) {
            access -= Opcodes.ACC_FINAL
            println("XXASMClassVisitor : visit final Field :" + name)
        }
        if ((Opcodes.ACC_PROTECTED & access) == Opcodes.ACC_PROTECTED) {
            access -= Opcodes.ACC_PROTECTED
            access += Opcodes.ACC_PUBLIC
            println("XXASMClassVisitor : visit private Field ：" + name)
        }
        if ((Opcodes.ACC_PRIVATE & access) == Opcodes.ACC_PRIVATE) {
            access -= Opcodes.ACC_PRIVATE
            println("XXASMClassVisitor : visit private Field ：" + name)
        }
        return super.visitField(access, name, desc, signature, value)
    }
}
