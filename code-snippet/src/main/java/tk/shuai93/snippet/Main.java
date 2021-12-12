package tk.shuai93;

import lombok.experimental.ExtensionMethod;

import java.util.Arrays;

// here we are importing java.util.Arrays and a custom Objects class (seen below)
@ExtensionMethod({Arrays.class, Objects.class})
public class Main {
    public static void main(String[] args) {
        new Main().print("Doe");
    }

    // example of null coalescence in Java
    private void print(String name) {
        // "Joe Doe" if name is null
        String value = name.orElse("John Doe");
        System.out.println(value);
    }

    // example of multiple chained extension methods
    private void copySort() {
        long[] values = new long[] { 5, 9, 2, 7 };

        // use Array static methods as extension methods
        values.copyOf(3).sort();
    }

    // example of how to write your own extension methods
    private boolean customExtension(String s) {
        return s.isOneOf(new Object[]{(Object)"foo", (Object)"bar"});
    }
}


class Objects {

    public static boolean isOneOf(Object object, Object[] values) {
        if (values != null)
            for (Object value : values)
                if (object.equals(value))
                    return true;

        return false;
    }

    public static <T> T orElse(final T value, final T elseValue) {
        return value != null ? value : elseValue;
    }
}