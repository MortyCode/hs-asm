package top.rcode.biz;

/**
 * @author ：河神
 * @date ：Created in 2022/4/15 17:02
 */
public class ClassCodeDemo {

    public int a;
    public boolean b;

    public int m1(){
        int a1 = ++a;
        int a2 = 1 + a1;
        System.out.println(a2);
        return a2;
    }

}
