package cn.jasonone.model;

import cn.jasonone.exceptions.BaseException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应结果对象
 *
 * @author xujie
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
public class HttpResult<D> {
    private int code;
    private String message;
    private D data;

    /**
     * 添加数据到data中(仅在data为Map类型时有效)
     *
     * @param name
     * @param data
     * @return
     */
    public HttpResult put(String name, Object data) {
        if (this.data instanceof Map) {
            ((Map) this.data).put(name, data);
        }
        return this;
    }

    /**
     * 创建一个成功的响应结果对象
     *
     * @param <D>
     * @return
     */
    public static <D> HttpResult<D> ok() {
        return new HttpResult<D>().setCode(0).setMessage("success");
    }

    /**
     * 创建一个成功的响应结果对象,并指定返回数据
     *
     * @param data
     * @param <D>
     * @return
     */
    public static <D> HttpResult<D> ok(D data) {
        return new HttpResult<D>().setCode(0).setMessage("success").setData(data);
    }

    /**
     * 创建一个成功的响应结果对象,并指定Map类型的返回数据
     *
     * @return
     */
    public static HttpResult<Map<String, Object>> okOfMap() {
        return new HttpResult<Map<String, Object>>().setCode(0).setMessage("success").setData(new HashMap<>(16));
    }

    /**
     * 创建一个失败的响应结果对象
     *
     * @param code
     * @param message
     * @param <D>
     * @return
     */
    public static <D> HttpResult<D> error(int code, String message) {
        return new HttpResult<D>().setCode(code).setMessage(message);
    }

    /**
     * 创建一个失败的响应结果对象
     *
     * @param e
     * @param <D>
     * @return
     */
    public static <D> HttpResult<D> error(BaseException e) {
        return new HttpResult<D>().setCode(e.getCode()).setMessage(e.getMessage()).setData((D) e.getData());
    }
}
