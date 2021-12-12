package tk.shuai93.snippet;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        // Optional 最佳实践

        Optional<String> strOpt = Optional.of("Hello World");
        //
        strOpt.ifPresent(System.out::println);

        print("hello world");
        print(null);



    }

    public static void print(String str) {
        var r1 = Optional.ofNullable(str).orElse("hello Java");
        System.out.println("orElse " + r1);
        var r2 = Optional.ofNullable(str).orElseGet(() -> "hello Java");
        System.out.println("orElseGet " + r2);

        String r3 = Optional.ofNullable(str).orElseThrow(
                () -> new IllegalArgumentException("Argument 'str' cannot be null or blank."));
        System.out.println("orElseThrow " + r2);

    }
}
