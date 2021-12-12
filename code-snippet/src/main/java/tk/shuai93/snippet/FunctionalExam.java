package tk.shuai93;

import lombok.experimental.ExtensionMethod;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class FunctionalExam {

    public static void main(String[] args) {

        // Q1: 生成一个 [a, b) 的等差数列，步长是 step（a、b、step 都是正数）然后生成一个对应字符串，以逗号空格分隔
        var a = 10;
        var b = 100;
        var step = 5;
        var delimiter = ", ";
        var res = Stream.iterate(a, x -> x < b, x -> x + step).
                map(java.util.Objects::toString).
                collect(Collectors.joining(delimiter));

        // 打印查看结果

        System.out.println("Q1 result：" + res);

        // Q2: <T, R> Stream<R> myMap(Stream<T> stream, Function<T, R> mapper) {}——用 Stream.of 和 flatMap 实现 Stream#map 的功能
        var res2 = Stream.iterate(a, x -> x < b, x -> x + step);

        var res3 = myMap(res2, x -> x + 1);
        System.out.println("Q2 result：" + res3.toList());


        // Q3: 题目要求一样， 入参为 Optional 类型
        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        // var v2 = Optional.of(List.of(1,2,3,4,5));
        var v2 = Optional.of("Hello World");

        var r4 = myMap(v2, x -> x);
        System.out.println("Q3 " + r4);

    }

    public static <T, R> Stream<R> myMap(Stream<T> stream, Function<T, R> mapper){
        return stream.flatMap( item -> Stream.of(mapper.apply(item)));
    }

    /** @noinspection OptionalUsedAsFieldOrParameterType*/
    // optional 不能够作为参数传递
    public static <T, R> Optional<R> myMap(Optional<T> optional, Function<T, R> mapper){
        return optional.flatMap( item -> Optional.of(mapper.apply(item)));
    }

//    public static <T, R> Stream<R> myMap1(Object obj, Function<T, R> mapper){
//        Stream<?> o = null;
//        if (obj instanceof Stream<?>) {
//            o = (Stream<?>) obj;
//
//        } else if (obj instanceof Optional<?>) {
//            o = ((Optional<?>) obj).stream();
//        }else {
//            return null;
//        }
//
//        return o.flatMap( item -> Stream.of(mapper.apply((T) item)));
//    }

}

@ExtensionMethod({Stream.class, MyStream.class})
class ExtensionStream{
    public static void main(String[] args) {

        // Q4: 题目要求一样参考 Q2， 要求 ExtensionMethod 使用 stream

        print();

    }

    public static void print () {
        var v1 = Stream.iterate(1, x -> x < 20, x -> x + 2);
        var r1 = v1.myMap(x -> x + 1);
        System.out.println("Q4 result：" + r1.toList());
    }

}


class MyStream{
    public static <T, R> Stream<R> myMap(Stream<T> stream, Function<T, R> mapper){
        return stream.flatMap( item -> Stream.of(mapper.apply(item)));
    }
}