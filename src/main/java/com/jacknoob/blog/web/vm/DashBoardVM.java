package com.jacknoob.blog.web.vm;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author JackJun
 * 2019/7/30 16:04
 * Life is just about survival.
 */
public class DashBoardVM {
    private Charts charts = new Charts();
    private String[][] tags;
    private Integer dayVisitsNum;
    private Integer weekVisitsNum;

    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    public String[][] getTags() {
        return tags;
    }

    public void setTags(String[][] tags) {
        this.tags = tags;
    }

    public Integer getDayVisitsNum() {
        return dayVisitsNum;
    }

    public void setDayVisitsNum(Integer dayVisitsNum) {
        this.dayVisitsNum = dayVisitsNum;
    }

    public Integer getWeekVisitsNum() {
        return weekVisitsNum;
    }

    public void setWeekVisitsNum(Integer weekVisitsNum) {
        this.weekVisitsNum = weekVisitsNum;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static class Charts {
        private List<Integer> weekVisits;

        public List<Integer> getWeekVisits() {
            return weekVisits;
        }

        public void setWeekVisits(List<Integer> weekVisits) {
            this.weekVisits = weekVisits;
        }
    }

}
