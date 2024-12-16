package com.zhang.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * @Author zhangtopsun@foxmail.com
 * @Description
 * @Date 2024/12/4
 */
@Slf4j
public class BaseUtil {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    /*
     * source int.
     *
     * @param source source
     * @return int
     * @see BaseUtil#str2Int(String, int)
     */
    public static int str2Int(final String source) {
        return str2Int(source, 0);
    }

    /*
     * String to integer if the conversion is abnormal use the default value
     * 字符串转整型如转换异常使用默认值.
     * @param source source
     * @param defautValue default value
     * @return int
     */
    public static int str2Int(final String source, final int defautValue) {
        int target = defautValue;
        try {
            target = Integer.parseInt(source);
        } catch (NumberFormatException ex) {
            log.warn(
                    "source:{} converts integer error :{}, returns default :{}",
                    source,
                    ex.getMessage(),
                    defautValue);
        }
        return target;
    }

    /*
     * Verify that source is empty, and return the default value if so.
     * 验证source是否为空,如果为空返回默认值.
     * @param source source
     * @param defaultStr default str
     * @return string
     */
    public static String resNotBlank(final String source, final String defaultStr) {
        String target = defaultStr;
        if (!ObjectUtils.isEmpty(source)) {
            target = source;
        }
        return target;
    }


    @SneakyThrows
    public static <T> T newInstance(final Class<T> targetClass) {
        T targetObj = targetClass.newInstance();
        return targetObj;
    }

    /*
     * str2Double.
     *
     * @param str String
     * @param nullVal double
     * @return double
     */
    public static double str2Double(final String str, final double nullVal) {
        double result;
        try {
            if (ObjectUtils.isEmpty(str)) {
                return nullVal;
            }
            result = Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            result = nullVal;
            log.warn(
                    "source:{} converts integer error :{}, returns default :{}",
                    str,
                    ex.getMessage(),
                    nullVal);
        }
        return result;
    }

    /*
     * str2BigDecimal.
     *
     * @param str String
     * @param nullVal BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal str2BigDecimal(final String str, final BigDecimal nullVal) {
        BigDecimal result;
        try {
            if (ObjectUtils.isEmpty(str)) {
                return nullVal;
            }
            result = new BigDecimal(str);
        } catch (NumberFormatException ex) {
            result = nullVal;
            log.warn("source:{} Convert error :{}, return default :{}", str, ex.getMessage(), nullVal);
        }
        return result;
    }

    /*
     * Converts the exception stack information to a string.
     * 把异常的堆栈信息转换为字符串.
     *
     * @param cause Throwable
     * @return string
     */
    public static final String getThrowableString(final Throwable cause) {
        if (cause == null) {
            return null;
        }

        StringWriter result = new StringWriter();
        try (PrintWriter writer = new PrintWriter(result)) {
            cause.printStackTrace(writer);
        }
        return result.toString();
    }
    /*
     * Round formatted amounts, thousandths, and keep two decimal places
     * 四舍五入格式化金额，千分位，并保留两位小数
     * @Param
     * @return
     */
    public static final String fmtMicrometer(BigDecimal number) {
        DecimalFormat df2 = new DecimalFormat("###,##0.00");
        String format = df2.format(number).replace(",", " ");
        return format;
    }

    /*
     * judge str is number or not
     * 判断字符串是否是数字
     * @Param
     * @return
     */
    public static final boolean isNumber(final String str) {
        return str != null && NUMBER_PATTERN.matcher(str).matches();
    }
}
