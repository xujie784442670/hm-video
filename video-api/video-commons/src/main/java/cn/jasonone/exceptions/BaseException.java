package cn.jasonone.exceptions;

import lombok.Data;
import lombok.ToString;

/**
 * 全局基本异常对象
 * @author xujie
 * @version 1.0.0
 */
@Data
@ToString(callSuper = true)
public class BaseException extends RuntimeException {
    private final int code;
    private Object data;
    public BaseException(int code, String message,Throwable e) {
        super(message,e);
        this.code = code;
    }
}
