package com.example.demo;

/**
 * 创建时间:2019/03/18
 * 创建人:pmc
 * 描述:
 */
public class test
{
    public static void main(String args[])
    {
        String a = "123";
        a=doTest3(a);
        boolean c = a==a;
        System.out.println(c + "->a:" + a);
    }

    private static String doTest3(String str)
    {
        str = str + "2";
        System.out.println("str:" + str);
        return str;
    }
}
