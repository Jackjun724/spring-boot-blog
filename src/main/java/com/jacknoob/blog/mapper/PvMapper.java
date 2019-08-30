package com.jacknoob.blog.mapper;

import com.jacknoob.blog.entity.Pv;

import java.util.List;
import java.util.Map;

public interface PvMapper {
    int insert(Pv record);

    int insertSelective(Pv record);

    List<Map<String, Object>> selectNow();

    void insertNow();

    void addOneNow();

    List<Map<String, Object>> getWeekVisits();

    Integer getDayVisitsNum();

    Integer getWeekVisitsNum();
}