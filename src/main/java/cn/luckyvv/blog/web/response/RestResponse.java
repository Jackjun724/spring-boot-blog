package cn.luckyvv.blog.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author JackJun
 * 2019/7/4 20:01
 * Life is not just about survival, but VV and distance.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    private String message;
    private T data;

    /* Static */

    public static <T> RestResponse<T> getResp(String message,T data){
        return new RestResponse<>(message,data);
    }

    public static <T> RestResponse<T> getResp(String message){
        return new RestResponse<>(message);
    }


    /* Constructor */

    RestResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    private RestResponse(String message) {
        this.message = message;
    }

    /* Getter And Setter */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
