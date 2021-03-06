package top.rcode.visitor;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author ：河神
 * @date ：Created in 2022/4/12 23:44
 */
public class PreMethordVisitor extends MethodVisitor {

    private MethodVisitor methodVisitor;

    public PreMethordVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM6, methodVisitor);
        this.methodVisitor = methodVisitor;
    }


    /**
     * 在方法返回前加字段
     * @param opcode
     */
    @Override
    public void visitInsn(int opcode) {
        if (opcode>= Opcodes.IRETURN && opcode<=Opcodes.RETURN){
            methodVisitor.visitFieldInsn(Opcodes.GETSTATIC,
                    Type.getInternalName(System.class),
                    "out",
                    Type.getDescriptor(System.out.getClass()));

            methodVisitor.visitLdcInsn("方法返回!");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                    Type.getInternalName(System.out.getClass()),
                    "println",
                    "(Ljava/lang/String;)V", false);
        }
        super.visitInsn(opcode);

    }

    /**
     * 在方法前面加代码
     */
    @Override
    public void visitCode() {

        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC,
                Type.getInternalName(System.class),
                "out",
                Type.getDescriptor(System.out.getClass()));

        methodVisitor.visitLdcInsn("方法头!");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(System.out.getClass()),
                "println",
                "(Ljava/lang/String;)V", false);

        super.visitCode();
    }

}
