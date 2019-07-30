package com.jacknoob.blog.web.exception;

import com.jacknoob.blog.web.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

/**
 * @author JackJun
 * 2019/7/3 11:52
 * Life is not just about survival.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(WebException.class)
    @ResponseBody
    public ResponseEntity<?> badRequest(WebException e) {
        logger.info("Bad Request Info: {},{}",e.getMessage(),e.getData());
        return new ResponseEntity<>(RestResponse.getResp(e.getMessage(), e.getData()), e.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse formValidateFailed(MissingServletRequestParameterException e) {
        logger.error("ParameterError:{}", e.getMessage());
        return RestResponse.getResp("参数错误!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse formValidateFailed(MethodArgumentNotValidException e){
        logger.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return RestResponse.getResp(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public RestResponse processMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return RestResponse.getResp(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception e) {
        ByteArrayOutputStream exceptionStream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(exceptionStream));
        String exceptionMsg = exceptionStream.toString();
        logger.error(exceptionMsg);
        return new ResponseEntity<>(RestResponse.getResp(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
