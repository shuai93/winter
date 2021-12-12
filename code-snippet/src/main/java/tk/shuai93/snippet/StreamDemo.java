package tk.shuai93.snippet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class StreamDemo {
    public static void main(String[] args) {
        // Q1: 求一个列表比如 var list = List.of(1, 2, 9, 8, 6, 6, 5, 3, 7); 的奇数和与偶数和

        // 类型推断
        var list = List.of(1, 2, 9, 8, 6, 6, 5, 3, 7);
        // Integer oddTotal1 = list.stream().filter(item -> (item & 1) == 1).reduce(Integer::sum).orElse(0);
        // Integer evenTotal1 = list.stream().filter(item -> (item & 1) == 0).reduce(Integer::sum).orElse(0);

        // (x, y) -> x + y   ==  Integer::sum
        // get() -> orElse(0)

        Integer oddTotal = list.stream().filter(item -> (item & 1) == 1).reduce(0, Integer::sum);
        Integer evenTotal = list.stream().filter(item -> (item & 1) == 0).reduce(0, Integer::sum);

        // Integer oddTotal = list.stream().filter(item -> (item & 1) == 0).mapToInt(Integer::intValue).sum();

        // partitioningBy
        Map<Boolean, Integer> collect = list.stream().collect(
                Collectors.partitioningBy(
                        x -> x % 2 == 0,
                        Collectors.summingInt(Integer::intValue)
                )
        );


        System.out.println("partitioningBy" + collect);
        System.out.println("奇数求和 " + oddTotal);
        System.out.println("偶数求和 " + evenTotal);

        var list1 = List.of(1, 2, 9, 8, 6, 6, 5, 3, 7, 9, 11, 20, 17, 6, 33, 25, 8, 0, 2);

        var res = list1.stream().map(x -> new Pair(x % 5, x)).collect(
                Collectors.groupingBy(
                        Pair::getFirst,
                        Collectors.summingInt(Pair::getSecond)
                )
        );

        var res1 = list1.stream().collect(
                Collectors.groupingBy(
                        x -> x % 5,
                        Collectors.summingInt(Integer::intValue)
                )
        );

        System.out.println(res1);
        System.out.println(res);


        // var list = List.of(1, 2, 9, 8, 6, 6, 5, 3, 7, 9, 11, 20, 17, 6, 33, 25, 8, 0, 2, 5, 10) 按 %5 的值分组求和。
        // 生成数据
        var l1 = Stream.iterate(0, n -> n + 1).limit(104).
                filter(item -> item % 3 == 1).collect(Collectors.toList());
        // iterate java9
        var l2 = Stream.iterate(1, x -> x < 104, x -> x+ 3).
                collect(Collectors.toUnmodifiableList());

        System.out.println(chunk1(l2));

        // 泛化
        var l4 = Stream.iterate(1, x -> x < 27, x -> x+ 1).map(i -> (char)(96+i)).
                collect(Collectors.toUnmodifiableList());

        System.out.println(chunk(l4, 5));

        // 接口方式实现函数式
        var r1 = remain(num -> num % 5, 8);

        // 重写方法使用
        var r2 = remain(new Func(){
            @Override
            public Integer remain(Integer num){
                return num % 5;
            }
        }, 8);

        System.out.println(r1);
    }

    public static List<List<Integer>> chunk(List<Integer> list) {

        var chunkSize = 5;
        int limit = (list.size() + chunkSize - 1) / chunkSize;

        return Stream.iterate(0, x -> x < limit, x -> x+ 1)
                .map(item ->
                        list.stream().skip((long) item * chunkSize).limit(chunkSize).collect(Collectors.toUnmodifiableList())
                ).collect(Collectors.toUnmodifiableList());

    }

    public static List<List<Integer>> chunk1(List<Integer> list) {

        var chunkSize = 5;
        return Stream.iterate(0, x -> x < list.size(), x -> x + chunkSize).
                map( pos ->
                        list.stream().skip(pos).limit(chunkSize).collect(Collectors.toUnmodifiableList())
                ).collect(Collectors.toUnmodifiableList());
    }

    public static <T> List<List<T>> chunk(List<T> list, int chunkSize) {
        return Stream.iterate(0, x -> x < list.size(), x -> x + chunkSize).
                map( pos ->
                        list.stream().skip(pos).limit(chunkSize).collect(Collectors.toUnmodifiableList())
                ).collect(Collectors.toUnmodifiableList());
    }

    public static Integer remain(Func func, Integer num) {
        return func.remain(num);

    }

    @FunctionalInterface
    public interface Func {
        Integer remain(Integer num);
    }

    public static class Pair {

        private final Integer first;
        private final Integer second;

        Pair(Integer first, Integer second) {

            this.first = first;
            this.second = second;
        }
        public Integer getFirst() {
            return  this.first;
        }

        public Integer getSecond() {
            return  this.second;
        }
    }
}

