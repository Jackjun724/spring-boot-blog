package com.jacknoob.blog.web.controller;

import com.jacknoob.blog.common.Constants;
import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.service.BlogService;
import com.jacknoob.blog.web.response.Page;
import com.jacknoob.blog.web.util.ResponseUtils;
import com.jacknoob.blog.web.vm.NoteVM;
import com.jacknoob.blog.web.vm.TagNoteVM;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
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
    @Inject
    private BlogService blogService;

    @GetMapping(value = {"/index", "/", "/index/{page}"})
    public String homePage(Map<String, Object> map, @PathVariable(required = false) Integer page) {
        blogService.pv();
        Page<Map<String, Object>> pageVM = new Page<>();
        List<Map<String, Object>> result = blogService.getNoteListByPage(pageVM, Constants.HOME_PAGE_SIZE, page == null ? 1 : page);
        ResponseUtils.assemblyRefMap(map, ResponseUtils.assemblyPage(pageVM, result));
        return "pages/home";
    }

    @GetMapping("/api/loadTimeline")
    @ResponseBody
    public Page loadMore(@RequestParam("page") int page) {
        Page<Map<String, Object>> pageResult = new Page<>();
        page = Math.max(page,2);
        pageResult.setPageSize(Constants.TIME_LINE_PAGE_SIZE);
        Map<String, Object> map = new HashMap<>(16);
        return ResponseUtils.assemblyPage(pageResult, blogService.loadMoreByPage(page, pageResult));
    }

    @GetMapping("/timeline")
    public String timeLine(Map<String, Object> map) {
        Page pageResult = new Page();
        pageResult.setPageSize(5);
        ResponseUtils.assemblyRefMap(map, blogService.loadMoreByPage(1, pageResult));
        return "pages/time-line";
    }

    @GetMapping("/tags")
    public String tags(Map<String, Object> map) {
        ResponseUtils.assemblyRefMap(map, blogService.findAllTags());
        return "pages/tag/index";
    }

    @GetMapping("/note/{id}")
    public String note(@PathVariable("id") Integer id, Map<String, Object> map) {
        //插入访问记录
        blogService.noteClick(id);
        Note note = blogService.getNoteByIdAndLastUpdateTimestamp(id);
        ResponseUtils.assemblyRefMap(map, new NoteVM(blogService.getClickNum(id), note, note.getContent().length()));
        return "pages/note";
    }

    @GetMapping("/tags/{id}")
    public String tag(@PathVariable("id") Integer id, Map<String, Object> map) {
        List<Note> notes = blogService.getNoteByTagId(id);
        ResponseUtils.assemblyRefMap(map, new TagNoteVM(blogService.getTagNameById(id), notes));
        return "pages/tag/list";
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