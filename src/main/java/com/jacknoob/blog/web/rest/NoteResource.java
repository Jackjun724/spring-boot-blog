package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.service.NoteService;
import com.jacknoob.blog.web.response.PageResponse;
import com.jacknoob.blog.web.vm.EditNoteVM;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JackJun
 * 2019/7/30 16:47
 * Life is just about survival.
 */
@RestController
@RequestMapping("/api/note")
public class NoteResource {

    private final NoteService noteService;

    public NoteResource(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    @ApiOperation("发布文章")
    public ResponseEntity addNote(@RequestBody @Valid EditNoteVM note) {
        return ResponseEntity.ok(noteService.addNote(note));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除文章")
    public ResponseEntity delNote(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(noteService.delNote(id));
    }

    @PutMapping
    @ApiOperation("修改文章")
    public ResponseEntity editNote(@RequestBody @Valid EditNoteVM note) {
        return ResponseEntity.ok(noteService.editNote(note));
    }

    @GetMapping
    @ApiOperation("查询文章列表")
    public ResponseEntity getNoteList(@ApiParam Pageable page
            , @RequestParam(value = "filters", required = false) List<String> filters
            , @RequestParam(value = "orderByColumn", required = false, defaultValue = "id") String orderByColumn
            , @RequestParam(value = "orderType", required = false, defaultValue = "false") Boolean isDesc) {
        filters=filters==null?new ArrayList<>() :filters;
        return ResponseEntity.ok(PageResponse.getResp("获取成功!", noteService.noteList(page, filters, orderByColumn, isDesc)));
    }

    @GetMapping("/by")
    @ApiOperation("根据ID查询文章")
    public ResponseEntity getNoteById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
}
