package tk.shuai93.winter.common.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @Author: gang.chen
 * @CreateTime: 2021-08-31 15:39
 * @Description: description
 **/
public class MyBatisUtil {
	public MyBatisUtil() {
	}

	public static <T> String getFieldName(EntityGetterMethod<T, Object> expression) {
		if (expression == null) {
			throw new IllegalArgumentException("Expression should not be null");
		} else {
			try {
				Method method = expression.getClass().getDeclaredMethod("writeReplace");
				method.setAccessible(Boolean.TRUE);
				SerializedLambda serializedLambda = (SerializedLambda) method.invoke(expression);
				String fieldName = StringUtil.resolveFieldName(serializedLambda.getImplMethodName());
				String className = serializedLambda.getImplClass().replace("/", ".");
				Field field = ReflectionUtils.findField(Class.forName(className), fieldName);
				String columnName = field.getName();
				TableField[] tableField = (TableField[]) field.getAnnotationsByType(TableField.class);
				if (null != tableField && tableField.length == 1 && !StringUtils.isEmpty(tableField[0].value())) {
					columnName = tableField[0].value();
				}

				String ret = StringUtils.camelToUnderline(columnName);
				return ret;
			} catch (ReflectiveOperationException var9) {
				throw new RuntimeException("This will never happen!", var9);
			}
		}
	}

	public interface EntityGetterMethod<T, R> extends Function<T, R>, Serializable {
	}
}
