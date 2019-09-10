package com.jacknoob.blog.common;

import java.util.Arrays;
import java.util.HashSet;

public enum FileType {
    Image(0),
    Other(1);

    private static HashSet<Integer> typeSet = new HashSet<>(Arrays.asList(0, 1));
    private int status;

    FileType(int status) {
        this.status = status;
    }

    public static boolean validType(int type) {
        return typeSet.contains(type);
    }

    public int getType() {
        return status;
    }
}
