package com.jacknoob.blog.web.exception;

import org.springframework.http.HttpStatus;

/**
 * @author JackJun
 * 2019/7/2 9:06
 * Life is not just about survival.
 */
public class WebException extends RuntimeException {
    private static final long serialVersionUID = 1L ;

    private String message;
    private HttpStatus status;
    private Object data;

    public WebException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public WebException(String message, HttpStatus status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
