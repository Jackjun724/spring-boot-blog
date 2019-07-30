package com.jacknoob.blog.web.controller;

import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.service.BlogService;
import com.jacknoob.blog.web.response.Page;
import com.jacknoob.blog.web.util.ResponseUtils;
import com.jacknoob.blog.web.vm.NoteVM;
import com.jacknoob.blog.web.vm.TagVM;
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

    @GetMapping("/index/{page}")
    public String homePage(@PathVariable("page") Integer page, Map<String, Object> map) {
        Page<Note> pageVM = new Page<>();
        List<Map<String, Object>> result = blogService.getNoteListByPage(pageVM, Constants.HOME_PAGE_SIZE, page);
        ResponseUtils.assemblyRefMap(map, result, pageVM);
        return "home/index";
    }

    @GetMapping({"/index", "/"})
    public String homePage(Map<String, Object> map) {
        blogService.pv();
        Page<Note> pageResult = new Page<>();
        List<Map<String, Object>> result = blogService.getNoteListByPage(pageResult, Constants.HOME_PAGE_SIZE, 1);
        ResponseUtils.assemblyRefMap(map, result, pageResult);
        return "home/index";
    }

    @GetMapping("/api/loadTimeline")
    @ResponseBody
    public Map<String, Object> loadMore(int page) {
        Page pageResult = new Page();
        pageResult.setPageSize(Constants.TIME_LINE_PAGE_SIZE);
        Map<String, Object> map = new HashMap<>(16);
        ResponseUtils.assemblyRefMap(map, blogService.loadMoreByPage(page, pageResult), pageResult);
        return map;
    }

    @GetMapping("/timeline")
    public String timeLine(Map<String, Object> map) {
        Page pageResult = new Page();
        pageResult.setPageSize(5);
        ResponseUtils.assemblyRefMap(map, blogService.loadMoreByPage(1, pageResult));
        return "timeline/index";
    }

    @GetMapping("/tags")
    public String tags(Map<String, Object> map) {
        ResponseUtils.assemblyRefMap(map, blogService.findAllTags());
        return "tags/index";
    }

    @GetMapping({"/note/{id}/{time}", "/note/{id}"})
    public String note(@PathVariable("id") Integer id, @PathVariable(name = "time", required = false) Long time, Map<String, Object> map) {
        //插入访问记录
        blogService.noteClick(id);
        Note note = blogService.getNoteByIdAndLastUpdateTimestamp(id, time);
        ResponseUtils.assemblyRefMap(map, new NoteVM(blogService.getClickNum(id), note, note.getContent().length()));
        return "note/index";
    }

    @GetMapping("/tags/{id}")
    public String tag(@PathVariable("id") Integer id, Map<String, Object> map) {
        List<Note> notes = blogService.getNoteByTagId(id);
        ResponseUtils.assemblyRefMap(map, new TagVM(blogService.getTagNameById(id), notes));
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