package com.github.shuai93.extra;

import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author 杨帅
 * @Date 2021/8/23 下午3:18
 * @Version 1.0
 */
public class StreamDemo {
    public static void main(String[] args) {


        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // 遍历列表
        list.forEach(System.out::print);
        // 123456789
        System.out.println();

        // 求和
//        Integer sumResult = list.stream().reduce(0, (a, b) -> a + b);

        Integer sumResult = list.stream().reduce(0, Integer::sum);
        System.out.println(sumResult);

        // 返回元素的平方
        List<Integer> numbersList = list.stream().map(i -> i * i).collect(Collectors.toList());

        // [1, 4, 9, 16, 25, 36, 49, 64, 81]
        System.out.println(numbersList);


        List<Integer> evenNumbersList = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        // 筛选出列表中的偶数 [2, 4, 6, 8]
        System.out.println(evenNumbersList);

        // 筛选出数组中的奇数并返回元素的平方
        List<Integer> numbers = list.stream().filter(i -> i % 2 != 0).map(i -> i * i).collect(Collectors.toList());
        System.out.println(numbers);


        List<String> language = List.of("Java", "Python", "Rust", "Go", "C++", "C", "C#", "JavaScript");

        // 将数组中元素转化成大写字母并用逗号拼接
        String collect1 = language.stream().collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
        // JAVA,PYTHON,RUST,GO,C++,C,C#,JAVASCRIPT
        System.out.println(collect1);

        // 实现按数组元素进行分组
        Map<Integer, List<String>> collect2 = language.stream().collect(Collectors.groupingBy(String::length));
        // {1=[C], 2=[Go, C#], 3=[C++], 4=[Java, Rust], 6=[Python], 10=[JavaScript]}
        System.out.println(collect2);

        // 转换成字符串长度与字符串的 hash map 有冲突后者覆盖前者
        Set<String> collect3 = language.stream().collect(Collectors.toCollection(HashSet::new));
        // language.stream().collect(Collectors.toSet());
        // [C#, Java, C++, Rust, C, JavaScript, Go, Python]
        System.out.println(collect3);


        // 转换成字符串长度与字符串的 hash map 有冲突后者覆盖前者
        Map<Integer, String> collect4 = language.stream().collect(Collectors.toMap(String::length, String::new, (existing, replacement) -> existing));
        // {1=C, 2=Go, 3=C++, 4=Java, 6=Python, 10=JavaScript}
        System.out.println(collect4);


        Person p1 = new Person.PersonBuilder().name("bob").height(150).city("shanghai").age(15).build();
        Person p2 = new Person.PersonBuilder().name("andy").height(160).city("beijing").age(18).build();
        Person p3 = new Person.PersonBuilder().name("tom").height(170).city("xian").age(19).build();
        Person p4 = new Person.PersonBuilder().name("cat").height(180).city("shanghai").age(25).build();


        ArrayList<Person> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);


        Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);

        Map<String, Person> collect = persons.stream().peek(item -> {
            String temp = item.getAge() >= 18 ? "成年" : "未成年";
            item.setExtra(temp);
        }).collect(
                Collectors.groupingBy(
                        Person::getCity,
                        Collectors.reducing(p1, BinaryOperator.maxBy(byHeight))
                )
        );

        System.out.println(collect);

    }


}


@Builder(toBuilder = true)
@Data
class Person {
    private Integer height;
    private Integer age;
    private String name;
    private String city;
    private String extra;

}