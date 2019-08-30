package com.jacknoob.blog.web.vm;

import com.alibaba.fastjson.JSON;
import com.jacknoob.blog.entity.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JackJun
 * 2019/7/30 16:04
 * Life is just about survival.
 */
public class DashBoardVM {
    private Charts charts = new Charts();
    private List<Reply> reply = new ArrayList<>();
    private String[][] tags;
    private Integer dayVisitsNum;
    private Integer weekVisitsNum;
    private Integer replyNum;

    public Charts getCharts() {
        return charts;
    }

    public void setCharts(Charts charts) {
        this.charts = charts;
    }

    public List<Reply> getReply() {
        return reply;
    }

    public void setReply(List<Reply> reply) {
        this.reply = reply;
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

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public class Charts {
        private List<Integer> weekVisits;
        private List<Integer> weekReply;

        public List<Integer> getWeekVisits() {
            return weekVisits;
        }

        public void setWeekVisits(List<Integer> weekVisits) {
            this.weekVisits = weekVisits;
        }

        public List<Integer> getWeekReply() {
            return weekReply;
        }

        public void setWeekReply(List<Integer> weekReply) {
            this.weekReply = weekReply;
        }
    }

}
