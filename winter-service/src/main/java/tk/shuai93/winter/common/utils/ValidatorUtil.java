package tk.shuai93.winter.common.utils;

import com.google.common.base.Joiner;
import org.springframework.util.ObjectUtils;
import tk.shuai93.winter.common.base.BaseException;
import tk.shuai93.winter.common.base.ResponseCode;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ValidatorUtil {
	private static final Validator VALIDATOR =
		Validation.buildDefaultValidatorFactory().getValidator();

	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new BaseException(ResponseCode.VALIDATOR_ERROR.code, message);
		}
	}

	public static void isTrue(boolean expression, ResponseCode responseCode) {
		if (!expression) {
			throw new BaseException(responseCode);
		}
	}

	public static void isFalse(boolean expression, ResponseCode responseCode, String message) {
		if (expression) {
			throw new BaseException(responseCode.code, message);
		}
	}

	public static void isFalse(boolean expression, String message) {
		if (expression) {
			throw new BaseException(ResponseCode.VALIDATOR_ERROR.code, message);
		}
	}

	public static void isFalse(boolean expression, ResponseCode responseCode) {
		if (expression) {
			throw new BaseException(responseCode);
		}
	}

	public static void isTrue(boolean expression, ResponseCode responseCode, String message) {
		if (!expression) {
			throw new BaseException(responseCode.code, message);
		}
	}

	public static void notEmpty(Object obj, String message) {
		if (ObjectUtils.isEmpty(obj)) {
			throw new BaseException(ResponseCode.VALIDATOR_ERROR.code, message);
		}
	}

	public static void notEmpty(Object obj, ResponseCode responseCode) {
		if (ObjectUtils.isEmpty(obj)) {
			throw new BaseException(responseCode);
		}
	}

	public static void notEmpty(Object obj, ResponseCode responseCode, String message) {
		if (ObjectUtils.isEmpty(obj)) {
			throw new BaseException(responseCode.code, message);
		}
	}

	public static void isEmpty(Object obj, String message) {
		if (!ObjectUtils.isEmpty(obj)) {
			throw new BaseException(ResponseCode.VALIDATOR_ERROR.code, message);
		}
	}

	public static void isEmpty(Object obj, ResponseCode responseCode) {
		if (!ObjectUtils.isEmpty(obj)) {
			throw new BaseException(responseCode);
		}
	}

	public static void isEmpty(Object obj, ResponseCode responseCode, String message) {
		if (!ObjectUtils.isEmpty(obj)) {
			throw new BaseException(responseCode.code, message);
		}
	}

	/**
	 * 校验实体参数是否符合要求
	 *
	 * @param t
	 * @param <T>
	 */
	public static <T> void validate(T t) {
		if (t == null) {
			throw new IllegalArgumentException("参数表单为空");
		}
		Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(t, Default.class);
		List<String> errMsg = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
		if (!errMsg.isEmpty()) {
			throw new IllegalArgumentException(Joiner.on(",").join(errMsg));
		}
	}
}
