package top.rcode.biz;

import com.alibaba.fastjson.JSON;

/**
 * @author ：河神
 * @date ：Created in 2022/4/11 13:46
 */
public class Hello{


    private String aStr;
    private Integer bInt;

    public int ha = 12;


    public String say(){
        int a = 1;
        System.out.println(a);
        return "hello";
    }

    public void out1(){
        int a = 1;
        System.out.println(a);
    }



    public void str(){
        long time1,time2,time3;
        time1 = System.currentTimeMillis();

        System.out.println(JSON.toJSONString(this));

        time2 = System.currentTimeMillis();
        time3 = time2 - time1;
        System.out.println(time3);
    }

    public String px(String p1,String p2,String p3) {
        return "look:";
    }


}
