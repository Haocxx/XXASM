package com.haocxx.xxasm.plugin.traversal

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * Created by Haocxx
 * on 2019-09-12
 */
class XXASMTraversalClassVisitor extends ClassVisitor {

    private String mClassName

    XXASMTraversalClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv)
    }

    @Override
    void visit(int version, int access, String name, String signature,
               String superName, String[] interfaces) {
        // save current visit class name
        this.mClassName = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor result
        result = super.visitMethod(access, name, desc, signature, exceptions)
        if (access == Opcodes.ACC_PRIVATE && "<init>" != name && !name.startsWith("lambda\$")) {
            XXASMTraversalManager._instance.sPrivateMethodSet.add(new XXASMTraversalManager.MethodInfo(mClassName, name))
        }
        if ((Opcodes.ACC_SYNTHETIC & access) == Opcodes.ACC_SYNTHETIC && name.startsWith("access\$")) {
            result = result == null ? null : new XXASMTraversalMethodVisitor(mClassName, name, result)
        }
        return result
    }

    @Override
    FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return super.visitField(access, name, desc, signature, value)
    }
}
