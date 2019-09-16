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
        super(Opcodes.ASM4, cv)
    }

    @Override
    void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        System.out.println("XXASMClassVisitor : visit -----> started ：" + name)
        // save current visit class name
        this.mClassName = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (Opcodes.ACC_PROTECTED == access) {
            System.out.println("XXASMClassVisitor : visit private Method ：" + name)
            return super.visitMethod(Opcodes.ACC_PUBLIC, name, desc, signature, exceptions)
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (Opcodes.ACC_PROTECTED == access) {
            System.out.println("XXASMClassVisitor : visit private Field ：" + name)
            return super.visitField(Opcodes.ACC_PUBLIC, name, desc, signature, value)
        }
        if (Opcodes.ACC_PRIVATE == access) {
            System.out.println("XXASMClassVisitor : visit private Field ：" + name)
            return super.visitField(0, name, desc, signature, value)
        }
        return super.visitField(access, name, desc, signature, value)
    }
}
