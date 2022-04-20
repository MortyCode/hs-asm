package top.rcode.asm;

import com.alibaba.fastjson.JSON;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import top.rcode.biz.Hello;
import top.rcode.visitor.HelloClassVisitor;

import java.io.IOException;

/**
 * @author ：河神
 * @date ：Created in 2022/4/11 13:38
 */
public class ReadClass {

    public static void main(String[] args) throws IOException {
        ClassVisitor classVisitor = new HelloClassVisitor(Opcodes.ASM5);
        ClassReader classReader = new ClassReader("top.rcode.biz.Hello");
        classReader.accept(classVisitor,0);

        String aaa = new Hello().say();
        System.out.println(aaa);

    }

}
