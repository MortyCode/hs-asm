package top.rcode.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import top.rcode.biz.Hello;
import top.rcode.visitor.PreClassVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ：河神
 * @date ：Created in 2022/4/12 23:43
 */
public class WriteClass {

    public static void main(String[] args) throws IOException {

        ClassReader classReader = new ClassReader("top.rcode.biz.Hello");
        PreClassVisitor classWriter = new PreClassVisitor( new ClassWriter(ClassWriter.COMPUTE_MAXS));
        classReader.accept(classWriter,0);

        Files.write(Paths.get("/Users/hailingliang/app/workspace/hs-asm/base/target/classes/top/rcode/biz/Hello.class"),
                classWriter.toByteArray());

        Hello hello = new Hello();
        hello.say();
    }

}
