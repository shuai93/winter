package tk.shuai93.winter.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import tk.shuai93.winter.common.base.BaseException;
import tk.shuai93.winter.common.base.ResponseCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Slf4j
public abstract class LocalDateTimeUtil {
	public static final String NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String NORMAL_PATTERN_YMD = "yyyy-MM-dd";
	public static final String COMPACT_PATTERN_YMD = "yyyyMMdd";
	public static final String COMPACT_LONG_PATTERN = "yyyyMMddHHmmss";

	public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
		if (ObjectUtils.isEmpty(dateTime)) {
			throw new BaseException(ResponseCode.DATE_IS_EMPTY);
		}
		try {
			return dateTime.format(DateTimeFormatter.ofPattern(pattern));
		} catch (Exception ex) {
			log.error("DateTime transfer error {}", ex.getMessage());
			throw new BaseException(ResponseCode.DATE_CONVERT_ERROR);
		}
	}

	public static String formatLocalDateTime(String dateTime, String pattern) {
		var date = convertToLocalDateTime(dateTime, pattern);
		return formatLocalDateTime(date, pattern);
	}

	public static String formatLocalDate(LocalDate date, String pattern) {
		if (ObjectUtils.isEmpty(date)) {
			throw new BaseException(ResponseCode.DATE_IS_EMPTY);
		}
		try {
			var result = date.format(DateTimeFormatter.ofPattern(pattern));
			return result;
		} catch (Exception ex) {
			log.error("DateTime transfer error {}", ex.getMessage());
			throw new BaseException(ResponseCode.DATE_CONVERT_ERROR);
		}
	}

	public static String formatLocalDate(String date, String pattern) {
		var dateStr = convertToLocalDate(date, pattern);
		var result = formatLocalDate(dateStr, pattern);
		return result;
	}

	public static LocalDateTime convertToLocalDateTime(String dateTime, String pattern) {
		if (ObjectUtils.isEmpty(dateTime)) {
			throw new BaseException(ResponseCode.DATE_IS_EMPTY);
		}
		try {
			var df = DateTimeFormatter.ofPattern(pattern);
			// 解决从mysql查询出来的Po日期带T，实体copy导致日期转换出错
			return LocalDateTime.parse(dateTime.replaceAll("T", " "), df);
		} catch (Exception ex) {
			log.error("DateTime transfer error {}", ex.getMessage());
			throw new BaseException(ResponseCode.DATE_CONVERT_ERROR);
		}
	}

	public static LocalDate convertToLocalDate(String dateTime, String pattern) {
		if (ObjectUtils.isEmpty(dateTime)) {
			throw new BaseException(ResponseCode.DATE_IS_EMPTY);
		}
		try {
			var df = DateTimeFormatter.ofPattern(pattern);
			// 解决从mysql查询出来的Po日期带T，实体copy导致日期转换出错
			return LocalDate.parse(dateTime, df);
		} catch (Exception ex) {
			log.error("DateTime transfer error {}", ex.getMessage());
			throw new BaseException(ResponseCode.DATE_CONVERT_ERROR);
		}
	}

	public static LocalDateTime getNow() {
		return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
	}

	/**
	 * 返回日期字符串yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 */
	public static String getNowString() {
		return formatLocalDateTime(getNow(), NORMAL_PATTERN);
	}

	public static String getNowString(String pattern) {
		return formatLocalDateTime(getNow(), pattern);
	}

	public static String timeDiff(Long mills) {
		long day = mills / (24 * 60 * 60 * 1000);
		long hour = (mills / (60 * 60 * 1000) - day * 24);
		long min = ((mills / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long second = (mills / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		var result = new StringBuilder();
		if (day > 0L) {
			result.append(day);
			result.append("天");
		}
		if (hour > 0L) {
			result.append(hour);
			result.append("小时");
		}
		if (min > 0L) {
			result.append(min);
			result.append("分钟");
		}
		if (result.length() == 0) {
			result.append(second);
			result.append("秒钟");
		}
		return result.toString();
	}

	/**
	 * 返回 xxxx年xx月xx日
	 *
	 * @param dateTime
	 * @return
	 */
	public static String formatYMD(LocalDateTime dateTime) {
		if (ObjectUtils.isEmpty(dateTime)) {
			return "";
		}
		var year = dateTime.getYear();
		var month = dateTime.getMonthValue();
		var date = dateTime.getDayOfMonth();
		return String.format("%s年%s月%s日", year, month, date);
	}

	// 01. java.util.Date --> java.time.LocalDateTime
	public static LocalDateTime dateToLocalDateTime(Date date) {
		var instant = date.toInstant();
		var zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	// 02. java.util.Date --> java.time.LocalDate
	public static LocalDate dateToLocalDate(Date date) {
		var instant = date.toInstant();
		var zone = ZoneId.systemDefault();
		var localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalDate();
	}

	// 03. java.util.Date --> java.time.LocalTime
	public static LocalTime dateToLocalTime(Date date) {
		var instant = date.toInstant();
		var zone = ZoneId.systemDefault();
		var localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime.toLocalTime();
	}

	// 04. java.time.LocalDateTime --> java.util.Date
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		var zone = ZoneId.systemDefault();
		var instant = localDateTime.atZone(zone).toInstant();
		return Date.from(instant);
	}

	// 05. java.time.LocalDate --> java.util.Date
	public static Date localDateToDate(LocalDate localDate) {
		var zone = ZoneId.systemDefault();
		var instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}

}
