package com.jacknoob.blog.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author JackJun
 * 2019/10/6 18:56
 * Life is not just about survival.
 */
@Component
@ConfigurationProperties(prefix = "lean-cloud")
public class LeanCloud {
    private String appId = "";
    private String appKey = "";
    private String toMail = "jack@retzero.com";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getToMail() {
        return toMail;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }
}
