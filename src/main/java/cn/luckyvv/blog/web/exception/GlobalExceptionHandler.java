package cn.luckyvv.blog.web.exception;

import cn.luckyvv.blog.web.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.bind.ValidationException;
import java.util.Objects;

/**
 * @author JackJun
 * 2019/7/3 11:52
 * Life is not just about survival, but VV and distance.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse formValidateFailed(MethodArgumentNotValidException e){
        logger.error(e.getBindingResult().getFieldError().getDefaultMessage());
        return RestResponse.getResp(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public RestResponse processMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return RestResponse.getResp(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> globalException(RuntimeException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>(RestResponse.getResp(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
