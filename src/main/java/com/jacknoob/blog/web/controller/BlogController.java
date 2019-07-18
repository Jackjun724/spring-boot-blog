package com.jacknoob.blog.web.controller;

import com.alibaba.fastjson.JSON;
import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.service.BlogService;
import com.jacknoob.blog.web.response.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JackJun
 * 2019/7/18 9:24
 * Life is just about survival.
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    //TODO 处理数据对接 乱鸡儿魔法值  404 页面

    @GetMapping("/index/{page}")
    public String homePage(@PathVariable("page") Integer page, Map<String, Object> map) {
        Page<Note> pageVM = new Page<>();
        List<Map<String, Object>> result = blogService.getNoteListByPage(pageVM, Constants.HOME_PAGE_SIZE, page);
        map.put("page", pageVM);
        map.put("data", result);
        return "home/index";
    }

    @GetMapping({"/index", "/"})
    public String homePage(Map<String, Object> map) {
        blogService.pv();
        Page<Note> pageVO = new Page<>();
        List<Map<String, Object>> result = blogService.getNoteListByPage(pageVO, Constants.HOME_PAGE_SIZE, 1);
        map.put("page", pageVO);
        map.put("data", result);
        return "home/index";
    }

    @GetMapping("/api/loadTimeline.do")
    @ResponseBody
    public Map<String, Object> loadMore(int page) {
        Page pageVO = new Page();
        pageVO.setPageSize(Constants.TIME_LINE_PAGE_SIZE);
        Map<String, Object> map = new HashMap<>(16);
        map.put("page", pageVO);
        map.put("data", blogService.loadMoreByPage(page, pageVO));
        return map;
    }

    @GetMapping("/timeline")
    public String timeLine(Map<String, Object> map) {
        Page pageVO = new Page();
        pageVO.setPageSize(5);
        map.put("data", JSON.toJSONString(blogService.loadMoreByPage(1, pageVO)));
        return "timeline/index";
    }

    @GetMapping("/tags")
    public String tags(Map<String, Object> map) {
        map.put("data", blogService.findAllTags());
        return "tags/index";
    }

    @GetMapping({"/note/{id}/{time}", "/note/{id}"})
    public String note(@PathVariable("id") Integer id, @PathVariable("time") Long time, Map<String, Object> map) {
        //TODO 处理接口规范性 乱鸡儿插入数据 全是魔法值
        //插入访问记录
        blogService.noteClick(id);
        Note note = blogService.getNoteByIdAndLastUpdateTimestamp(id, time);
        map.put("len", note.getContent().length());
        String content = JSON.toJSONString(note.getContent());
        content = content.substring(1, content.length() - 1);
        map.put("content", content);
        note.setContent("");
        map.put("note", note);
        //查询CLICK NUM
        map.put("click", blogService.getClickNum(id));
        return "note/index";
    }

    @GetMapping("/tags/{id}")
    public String tag(@PathVariable("id") Integer id, Map<String, Object> map) {
        List<Note> notes = blogService.getNoteByTagId(id);
        map.put("note", JSON.toJSONString(notes));
        map.put("tagName", blogService.getTagNameById(id));
        return "tags/list";
    }

    @GetMapping("/help/pay")
    public String pay(HttpServletRequest request, Map<String, Object> map) {
        final String userAgent = "User-Agent";

        if (request.getHeader(userAgent).toLowerCase().contains("micromessenger")) {
            map.put("http", "wx");
        } else if (request.getHeader(userAgent).toLowerCase().contains("alipay")) {
            map.put("http", "HTTPS://QR.ALIPAY.COM/FKX01930FXGT6UB7XSA55F");
        } else {
            map.put("http", "error");
        }
        return "pay";
    }
}