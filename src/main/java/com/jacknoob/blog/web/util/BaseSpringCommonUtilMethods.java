package com.jacknoob.blog.web.util;

import com.jacknoob.blog.web.schedule.ReplyTask;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

/**
 * @author JackJun
 * @date 2019/10/9 7:08 下午
 */
@Component
public final class BaseSpringCommonUtilMethods {

    private final ReplyTask replyTask;

    public BaseSpringCommonUtilMethods(ReplyTask replyTask) {
        this.replyTask = replyTask;
    }

    public HttpEntity getHttpRequestEntity(){
        return replyTask.getRequestEntity();
    }
}
