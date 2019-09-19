package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.entity.Tag;
import com.jacknoob.blog.service.TagService;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.util.ServiceExecuteResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author JackJun
 * 2019/7/30 16:36
 * Life is just about survival.
 */
@RestController
@RequestMapping("/api/tag")
public class TagResource {

    private final TagService tagService;

    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    @ApiOperation("获取所有标签")
    public ResponseEntity allTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @DeleteMapping("/{tag}")
    @ApiOperation("根据名称删除标签")
    public ResponseEntity deleteTagByName(@ApiParam("标签名称") @PathVariable("tag") String tagName) {
        ServiceExecuteResult result = tagService.delTagByName(tagName);
        return new ResponseEntity<>(RestResponse.getResp(result.getMessage(), result.getData()), result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    @ApiOperation("添加标签")
    public ResponseEntity addTag(@ApiParam("标签") @Valid @RequestBody Tag tag) {
        tag.setPublishTime(new Date(System.currentTimeMillis()));
        ServiceExecuteResult result = tagService.createTag(tag);
        return new ResponseEntity<>(RestResponse.getResp(result.getMessage(), result.getData()), result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
