package cn.jasonone.exceptions;

import cn.jasonone.model.HttpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author xujie
 * @version 1.0.0
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public HttpResult handleException(BaseException e) {
        return HttpResult.error(e);
    }

    @ExceptionHandler
    public HttpResult handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        HttpResult<Map<String, Object>> httpResult = HttpResult.okOfMap();
        httpResult.setCode(400);
        httpResult.setMessage("参数校验失败");
        for (ConstraintViolation<?> violation : violations) {
            httpResult.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return httpResult;
    }
    @ExceptionHandler
    public HttpResult handleException(MissingServletRequestPartException e) {
        HttpResult<Map<String, Object>> httpResult = HttpResult.okOfMap();
        httpResult.setCode(400);
        httpResult.setMessage("参数校验失败");
        httpResult.put(e.getRequestPartName(), e.getMessage());
        return httpResult;
    }
}
