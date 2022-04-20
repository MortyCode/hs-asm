package top.rcode.visitor;

import jdk.internal.org.objectweb.asm.Type;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author ：河神
 * @date ：Created in 2022/4/11 14:19
 */
public class HelloMethodVisitor extends MethodVisitor {


    public HelloMethodVisitor(int api) {
        super(api);
    }

    @Override
    public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
        super.visitLocalVariable(name, descriptor, signature, start, end, index);
        System.out.println("参数【"+index+"】Name:  "+name);
    }
}
