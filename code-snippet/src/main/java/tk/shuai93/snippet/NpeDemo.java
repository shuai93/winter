package tk.shuai93.snippet;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * NpeDemo
 * </p>
 *
 * @author shuai.yang1
 * @since 2021-12-11
 */
public class NpeDemo {

    public static void main(String[] args) {
        // reference: https://hltj.me/java/2021/01/09/java-exercise-nulls.html
        // 一些日常 npe 防御
        stringNpe(null);
        objectNpe(null);
        colNpe(null);

        var res = mapSome(1, x -> x+1);
        var res1 = mapSome1(null, x -> x);
        System.out.println("mapSome1 重构结果：" + res1);


        optionalNpe(null);
        optionalNpe(7);
        optionalNpe(6);

        stringUtils(null);
        objectUtils(null);
        collectionsUtils(null);
        var list1 = Arrays.asList(1, 2, -3, 9, 15);
        var set = new HashSet<Integer>();
        CollectionUtils.addAll(set, list1);

        var map = Map.of("hello", 1, "world", 2);
        System.out.println(map);
        HashMap<Integer, String> sites = new HashMap<>();

        // 往 HashMap 添加一些元素
        sites.put(1, "Google");
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        System.out.println("sites HashMap: " + sites);

        //检查 key 为 4 是否存在，不存在插入该 key/value 对
        // 使用 ! 符号来对布尔结果取相反的值
        if(!sites.containsKey(null)) {
            sites.put(4, "Wiki");
        }

    }

    private static void stringUtils(String temp){
       // System.out.println(getChoice(null,false));
       // System.out.println(getChoice("Middle", true));
        System.out.println("重构 getChoice 结果为：" + getChoice1(null,false));
        System.out.println("重构 getChoice 结果为：" + getChoice1("Middle", true));
    }

    private static void objectUtils(String temp){
        System.out.println("----分割线----");

       // System.out.println(toHex(10, false));
        System.out.println("重构 toHex 结果为：" + toHex1(10, false));
       // System.out.println(toHex(10, null));
        System.out.println("重构 toHex 结果为：" + toHex1(10, null));

        System.out.println("----分割线----");

       // System.out.println(tomorrowOf(Instant.ofEpochSecond(1568568760)));
        System.out.println("重构 tomorrowOf 结果为：" + tomorrowOf1(Instant.ofEpochSecond(1568568760)));

       // System.out.println(tomorrowOf(null));
        System.out.println("重构 tomorrowOf 结果为：" + tomorrowOf1(null));

        System.out.println("重构 tomorrowOf3 结果为：" + tomorrowOf3(Instant.ofEpochSecond(1568568760)));
        System.out.println("重构 tomorrowOf3 结果为：" + tomorrowOf3(null));

        System.out.println("重构 tomorrowOf4 结果为：" + tomorrowOf4(Instant.ofEpochSecond(1568568760)));
        System.out.println("重构 tomorrowOf4 结果为：" + tomorrowOf4(null));


    }

    private static void collectionsUtils(String temp){
        System.out.println("----分割线----");

        List<Integer> ids = List.of(101, 111, 191);
        // System.out.println(getIdsString(null));
        System.out.println("重构 getIdsString 结果为：" + getIdsString1(null));
        // System.out.println(getIdsString(ids));
        System.out.println("重构 getIdsString 结果为：" + getIdsString1(ids));

        System.out.println("----分割线----");
    }


    private static void stringNpe(String temp) {
        // 字符串 equals 防御 Exception
        if ("Hello".equals(temp)) {
            System.out.println("Hello world");
        }
    }

    private static void objectNpe(Object temp) {
        // 字符串 equals 防御 Exception
        if (Objects.equals("Hello", temp)) {
            System.out.println("Hello world");
        }
    }

    private static void colNpe(String temp) {
        // 不可变 Map 不能 null
        // var map1 = Map.of("abc", 10, "def", 20, temp, 30);

        // var list1 = Arrays.asList(1, 2, -3, 9, null, 15);
        // 不可变 Set 不能 null
        // var set1 = Set.copyOf(list1);

        // null 不能使用不可变集合 containsKey
        if ( null != temp && Map.of("hello", 1, "world", 2).containsKey(temp)) {
            System.out.println("either 'hello' or 'world'");
        }
    }
    @Nullable
    public static <T, U> U mapSome(@Nullable T x,@NonNull Function<T, U> mapper) {
        // 返回值以及参数可以加注解做校验 Idea 会做出对应的提醒
        return x == null ? null: mapper.apply(x);
    }

    public static <T, U>  U mapSome1( T x, Function<T, U> mapper) {
        // Option 实现  boolean ？ x1: x2;
        return (U) Optional.ofNullable(x).orElse((T) mapper.apply(x));
    }


    private static void optionalNpe(Integer temp) {
        // Optional 重构
        Integer xInt = Math.random() > 0.8 ? null : Math.random() > 0.5 ? 5 : 12;
        // result
        var yInt = Optional.of(Math.random()).filter(a -> a < 0.8).map(
                b -> Optional.of(Math.random()).filter(c -> c > 0.5).map(d -> 5).orElse(12))
                .orElse(null);

        // System.out.println("xInt = " + xInt);
         System.out.println("重构三元表达式结果为：yInt = "+ yInt);

        // 重构以下代码
        var x1 = (temp == null || temp % 2 != 0) ? null : temp / 2;
        var x2 = Optional.ofNullable(temp).filter(x -> x % 2 == 0).map( y -> y / 2).orElse(null);
       // System.out.println("x1 = " + x1);
        System.out.println("重构三元表达式结果为：x2 = " + x2);

        System.out.println("Object 非空取出并 print ");
        if (x1 != null) {
            System.out.println(x1);
        }

        System.out.println("-----分割线0-----");
        Optional.ofNullable(xInt).filter(x -> x % 2 == 0).map( y -> y / 2).ifPresent(System.out::println);
        System.out.println("-----分割线0-----");
        Optional.ofNullable(temp).ifPresent(System.out::println);
        System.out.println("-----分割线-----");


        System.out.println("----分割线----");


       // System.out.println(getUpperTitle(new Post()));
        System.out.println("重构 getUpperTitle 结果为：" + getUpperTitle1(new Post()));
       // System.out.println(getUpperTitle(null));
        System.out.println("重构 getUpperTitle 结果为：" + getUpperTitle1(null));
        System.out.println("重构 getUpperTitle2 结果为：" + getUpperTitle2(new Post()));
        System.out.println("重构 getUpperTitle2 结果为：" + getUpperTitle2(null));



    }
    public static Instant tomorrowOf2( Instant x) {
        if (ObjectUtils.isEmpty(x)) {
            // log.debug("the base Date is null");
            x = Instant.now();
        }
        return  x.plus(Duration.ofDays(1));
    }
    public static Instant tomorrowOf3( Instant x) {

        return Optional.ofNullable(x).
                map(obj -> obj.plus(Duration.ofDays(1))).
                orElseGet(() -> {System.out.println("the base Date is null");return Instant.now().plus(Duration.ofDays(1));});

    }

    public static Instant tomorrowOf4( Instant x) {

        // return  ObjectUtils.getFirstNonNull(
        //         () -> x,
        //         () -> {
        //             System.out.println("the base Date is null");
        //             return Instant.now();
        //         }).plus(Duration.ofDays(1));

        return ObjectUtils.getIfNull(x,
                () -> {
                    System.out.println("the base Date is null");
                    return Instant.now();
                }).plus(Duration.ofDays(1));

    }

    public static Instant tomorrowOf1( Instant x) {
        return ObjectUtils.isEmpty(x) ? Instant.now() : x.plus(Duration.ofDays(1));
    }
    public static Instant tomorrowOf(@Nullable Instant x) {
        if (x == null) {
            x = Instant.now();
        }

        return x.plus(Duration.ofDays(1));
    }

    public static String toHex1(int n, Boolean useUpper) {
        String s = Integer.toString(n, 16);
        return BooleanUtils.toBoolean(useUpper) ? s.toUpperCase() : s;
    }

    public static String toHex(int n, @Nullable Boolean useUpper) {
        String s = Integer.toString(n, 16);
        return useUpper != null && useUpper ? s.toUpperCase() : s;
    }

    private static final List<Integer> IMPLICIT_IDS = List.of(101, 111, 191);
    public static String getIdsString(@Nullable Collection<@NonNull Integer> ids) {
        if (ids == null) {
            return IMPLICIT_IDS.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining());
        }

        return Stream.concat(ids.stream(), IMPLICIT_IDS.stream())
                .map(Object::toString)
                .collect(Collectors.joining());
    }


    public static String getIdsString1(@Nullable Collection<@NonNull Integer> ids) {
        ids = CollectionUtils.emptyIfNull(ids);
        return Stream.concat(ids.stream(), IMPLICIT_IDS.stream())
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static String getUpperTitle1(Post post) {
        return Optional.ofNullable(post)
                .map(Post::getTitle)
                .orElseGet(() -> {
                    System.out.println("no title");
                    return "- UNTITLED -";
                })
                .toUpperCase();
    }

    @NonNull
    private static String getUpperTitle2(@Nullable Post post) {
        var temp = Optional.ofNullable(post).flatMap(item -> Optional.ofNullable(item.getTitle()));
        if (temp.equals(Optional.empty())) {
            // log.warning("no title");
        }
        return temp.orElse("- UNTITLED -").toUpperCase();

    }


    @NonNull
    private static String getUpperTitle(@Nullable Post post) {
        if (post == null || post.getTitle() == null) {
            return "- UNTITLED -";
        }

        return post.getTitle().toUpperCase();
    }



    private static String getChoice(String choice, boolean highest) {
        if (choice != null && !choice.isEmpty())
            return choice;

        if (highest)
            return "High";

        return "Low";
    }

    private static String getChoice1(String choice, boolean highest) {
        return  StringUtils.isNotEmpty(choice) ? choice : highest ? "High": "Low";
    }





    static class Post {

        @Nullable
        String getTitle() {
            return "hello";
        }
    }
}


