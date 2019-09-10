package com.jacknoob.blog.common;

/**
 * @author JackJun
 * 2019/7/18 12:00
 * Life is just about survival.
 */
public class Constants {
    public static final int TIME_LINE_PAGE_SIZE = 5;

    public static final int HOME_PAGE_SIZE = 10;

    public static final String SPRING_PROFILE_SWAGGER = "swagger";

    public static final String GLOBAL_UPLOAD_PATH = "/data/upload/";

    public enum ResponseProp {
        /**
         * prop:分页属性
         */
        PAGE("page"),
        /**
         * prop:数据属性
         */
        DATA("data");

        private String propName;

        ResponseProp(String prop) {
            this.propName = prop;
        }

        public String getPropName() {
            return propName;
        }
    }
}
