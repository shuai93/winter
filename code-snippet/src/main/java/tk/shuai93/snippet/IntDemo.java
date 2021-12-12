package tk.shuai93.snippet;

/**
 * @Author 杨帅
 * @Date 2021/10/26 下午10:37
 * @Version 1.0
 */
public class IntDemo {

    public static void main(String[] args) {

        /*

        reference:
        https://zhuanlan.zhihu.com/p/338350987
        https://www.cnblogs.com/liuling/archive/2013/05/05/intandinteger.html

        ==：用于比较引用和比较基本数据类型时具有不同的功能，具体如下：

        （1）、基础数据类型：比较的是他们的值是否相等，比如两个int类型的变量，比较的是变量的值是否一样。

        （2）、引用数据类型：比较的是引用的地址是否相同，比如说新建了两个User对象，比较的是两个User的地址是否一样。
        */
        int num1 = 12345;
        int num2 = 12345;

        System.out.println(num1 == num2);

        Integer num3 = 12345;
        Integer num4 = 12345;

        System.out.println(num3 == num4);

        System.out.println("**************");


        int i = 128;
        Integer i1 = new Integer(128);

        Integer i2 = 128;
        Integer i3 = new Integer(128);
        //Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println("**************");
        Integer i5 = 127; // java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6); //true
        Integer n5 = 128;
        Integer n6 = 128;
        System.out.println(n5 == n6);//false*/
        Integer ii5 = new Integer(127);
        System.out.println(i5 == ii5); //false
        Integer i7 = new Integer(128);
        Integer i8 = new Integer(123);
        System.out.println(i7 == i8);  //false

    }
}
