package top.rcode.visitor;

import com.alibaba.fastjson.JSON;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * @author ：河神
 * @date ：Created in 2022/4/11 13:39
 */
public class HelloClassVisitor extends ClassVisitor {

    public HelloClassVisitor(int api) {
        super(api);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);

        System.out.println("==================类信息："+name);

        System.out.println("version:"+version);
        System.out.println("access:"+access);
        System.out.println("name:"+name);
        System.out.println("signature:"+signature);
        System.out.println("superName:"+superName);
        System.out.println("interfaces:"+ JSON.toJSONString(interfaces));
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("==================方法："+name);

        System.out.println("access:"+access);
        System.out.println("name:"+name);
        System.out.println("signature:"+signature);
        System.out.println("exceptions:"+ JSON.toJSONString(exceptions));
        return new HelloMethodVisitor(api);
    }
}
