package tk.shuai93.snippet;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author 杨帅
 * @Date 2021/11/12 上午12:11
 * @Version 1.0
 */
public class HashMapDemo {

    public static void main(String[] args) throws InterruptedException {
        Long temp = 1636038040367L;


        HashMap<Date, String> map = new HashMap<Date, String>();

        Date date1 = new Date();

        map.put(date1, "测试1");

        Thread.sleep(1000L);
        Date date2 = new Date();
        date2.setTime(temp);
        map.put(date2, "测试2");

        System.out.println("date1 identityHashCode is " + System.identityHashCode(date1));
        System.out.println("date2 identityHashCode is " + System.identityHashCode(date2));


        date1.setTime(temp);
        System.out.println(System.identityHashCode(date1));

        System.out.println("date1 hashcode is " + date1.hashCode());
        System.out.println("date2 hashcode is " + date2.hashCode());

        System.out.println("Hash map get date1 " + map.get(date1));
        System.out.println("Hash map get date2 " + map.get(date2));


        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        System.out.println(map);
        System.out.println(map.size());

        System.out.println("----------------- 分割线 -----------------");

        TwoArgIntOperator addTwoInts = (a, b) -> a + b;
        int result = method(addTwoInts);
        System.out.println("Result: " + result);

    }
    static int method(TwoArgIntOperator operator) {
        return operator.op(5, 10);
    }

    public interface TwoArgIntOperator {
        int op(int a, int b);
    }
}
