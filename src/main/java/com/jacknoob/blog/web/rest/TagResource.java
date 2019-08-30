package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.service.TagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author JackJun
 * 2019/7/30 16:36
 * Life is just about survival.
 */
@RestController
@RequestMapping("/api/tag")
public class TagResource {

    @Inject
    private TagService tagService;

    @Inject
    private TagMapper tagMapper;

    @GetMapping
    @ApiOperation("获取所有标签")
    public ResponseEntity allTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

}
