package cn.luckyvv.blog.web.exception;

/**
 * @author JackJun
 * 2019/7/2 9:06
 * Life is not just about survival, but VV and distance.
 */
public class WebException extends RuntimeException {
    private static final long serialVersionUID = 1L ;

    private String message;
    private int code;
    private Object data;

    public WebException(String message,int code){
        this.message = message;
        this.code = code;
    }

    public WebException(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
