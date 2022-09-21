package cn.jasonone.util;

import cn.hutool.core.util.StrUtil;
import cn.jasonone.exceptions.BaseException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具类
 *
 * @author xujie
 * @version 1.0.0
 */
public class Asserts {
    /**
     * 抛出异常
     *
     * @param code
     * @param message
     */
    public static void throwException(int code, String message,Throwable e) {
        throw new BaseException(code, message,e);
    }

    /**
     * 对象如果为null则抛出异常
     *
     * @param object
     * @param code
     * @param message
     */
    public static void notNull(Object object, int code, String message) {
        if (object == null) {
            throwException(code, message,null);
        }
    }

    /**
     * 对象如果为空则抛出异常
     * <pre>
     *     对象为null
     *     字符串为空
     *     集合为空
     *     数组为空
     * </pre>
     *
     * @param obj
     * @param code
     * @param message
     */
    public static void notEmpty(Object obj, int code, String message) {
        notNull(obj, code, message);
        if (obj instanceof String) {
            if (StrUtil.isEmpty((String) obj)) {
                throwException(code, message,null);
            }
        } else if (obj instanceof Collection) {
            if (((Collection) obj).isEmpty()) {
                throwException(code, message,null);
            }
        } else if (obj instanceof Map) {
            if (((Map) obj).isEmpty()) {
                throwException(code, message,null);
            }
        } else if (obj.getClass().isArray()) {
            if (((Object[]) obj).length == 0) {
                throwException(code, message,null);
            }
        }
    }

    /**
     * 字符串如果为空或者空格则抛出异常
     *
     * @param str
     * @param code
     * @param message
     */
    public static void notBlank(String str, int code, String message) {
        if (str == null || StrUtil.isBlank(str)) {
            throwException(code, message,null);
        }
    }

    /**
     * 如果不为true则抛出异常
     *
     * @param expression
     * @param code
     * @param message
     */
    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throwException(code, message,null);
        }
    }

}
