package tk.shuai93.winter.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


@Slf4j
public class StringUtil {
	private static Random random;

	static {
		try {
			random = SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		}
	}

	public static String getRandomCharAndNumber(Integer length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	public static int indexChinese(String s) {
		int indexChinese = 0;
		// 找第一个汉字
		for (int index = 0; index <= s.length() - 1; index++) {
			// 将字符串拆开成单个的字符
			String w = s.substring(index, index + 1);
			if (w.compareTo("\u4e00") > 0 && w.compareTo("\u9fa5") < 0) {// \u4e00-\u9fa5 中文汉字的范围
				indexChinese = index;
				break;
			}
		}
		return indexChinese;
	}

	public static String resolveFieldName(String getMethodName) {
		if (getMethodName.startsWith("get")) {
			getMethodName = getMethodName.substring(3);
		} else if (getMethodName.startsWith("is")) {
			getMethodName = getMethodName.substring(2);
		}

		return firstToLowerCase(getMethodName);
	}

	public static String firstToLowerCase(String param) {
		return isEmpty(param) ? "" : param.substring(0, 1).toLowerCase() + param.substring(1);
	}

	public static boolean isEmpty(final CharSequence cs) {
		int strLen;
		if (cs != null && (strLen = cs.length()) != 0) {
			for (int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(cs.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}

}
