package cn.jasonone.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HttpResult<T> {

    private int code;
    private String msg;
    private T data;


    public static HttpResult ok(){
        return new HttpResult().setCode(0).setMsg("ok");

    } public static <T> HttpResult<T> ok(T data){
        return new HttpResult<T>().setCode(0).setMsg("ok").setData(data);
    }

    public static HttpResult error(int code,String msg){
        return new HttpResult().setCode(code).setMsg(msg);
    }
}

