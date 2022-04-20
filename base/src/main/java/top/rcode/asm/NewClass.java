package top.rcode.asm;

import com.alibaba.fastjson.JSON;
import jdk.internal.org.objectweb.asm.Type;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import top.rcode.biz.BaseInterface;
import top.rcode.biz.Father;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ：河神
 * @date ：Created in 2022/4/12 14:01
 */
public class NewClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //创建基础信息
        String className = "top.rcode.biz.HeShen";

        ClassWriter classWriter = new ClassWriter(0);

        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                "top/rcode/biz/HeShen",
                null,
                Type.getInternalName(Father.class),
                new String[]{  Type.getInternalName(BaseInterface.class) });//接口



        //添加字段
        generateField(classWriter);
        //实现接口方法
        generateBaseMethod(classWriter);
        //重写父类方法
        generateFatherMethod(classWriter);
        //生成构造函数
        generateInit(classWriter);
        //生成结束
        classWriter.visitEnd();

        //存储class
        Files.write(Paths.get("/Users/hailingliang/app/workspace/hs-asm/base/target/classes/top/rcode/biz/HeShen.class"),
                classWriter.toByteArray());

        //获取创建的Class
        Class<?> heShenClazz = ClassLoader.getSystemClassLoader().loadClass("top.rcode.biz.HeShen");
        //通过反射创建
        Object o = heShenClazz.newInstance();
        Field[] declaredFields = heShenClazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!Modifier.isFinal(declaredField.getModifiers())){
                declaredField.set(o,"临时修改值1234");
            }
        }

        System.out.println(JSON.toJSONString(o));
    }


    static void generateField(ClassWriter classWriter){
        //添加Name字段
        classWriter.visitField(Opcodes.ACC_PUBLIC|Opcodes.ACC_STATIC|Opcodes.ACC_FINAL,
                "final", "Ljava/lang/String;", null, "初始值");

        classWriter.visitField(Opcodes.ACC_PUBLIC,
                "noFinal", "Ljava/lang/String;", null, "初始值");

    }

    static void generateFatherMethod(ClassWriter classWriter){
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "fatherSay",
                "()V", null, null);

        // 调用父类的方法
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL,
                Type.getInternalName(Father.class),
                "fatherSay",
                "()V", false);


        // 插入输出"SubClass sayHello"的字节码指令
        mv.visitFieldInsn(Opcodes.GETSTATIC,
                Type.getInternalName(System.class),
                "out",
                Type.getDescriptor(System.out.getClass()));
        mv.visitLdcInsn("SubClass sayHello");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(System.out.getClass()),
                "println",
                "(Ljava/lang/String;)V", false);


        mv.visitInsn(Opcodes.RETURN);
        // 设置局部变量表和操作数栈的大小
        mv.visitMaxs(2, 1);

    }


    static void generateBaseMethod(ClassWriter classWriter){
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "base",
                "()V", null, null);

        mv.visitFieldInsn(Opcodes.GETSTATIC,  Type.getInternalName(System.class),
                "out",
                Type.getDescriptor(System.out.getClass()));


        mv.visitLdcInsn("hello word!");

        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(System.out.getClass()),
                "println",
                "(Ljava/lang/String;)V", false);

        // void方法也需要有return指令
        mv.visitInsn(Opcodes.RETURN);
        // 设置局部变量表和操作数栈的大小
        mv.visitMaxs(2,1);
    }


    static void generateInit(ClassWriter classWriter){
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitCode();
        // 调用父类构造器
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,Type.getInternalName(Father.class),"<init>", "()V", false);
        // 添加一条返回指令
        methodVisitor.visitInsn(Opcodes.RETURN);
        // 设置操作数栈和局部变量表大小
        methodVisitor.visitMaxs(1,1);
        methodVisitor.visitEnd();
    }

}
