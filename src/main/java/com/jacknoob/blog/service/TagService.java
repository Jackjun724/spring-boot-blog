package com.jacknoob.blog.service;

import com.jacknoob.blog.entity.Tag;
import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.util.ServiceExecuteResult;
import com.jacknoob.blog.web.vm.TagVM;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JackJun
 * 2019/7/30 16:37
 * Life is just about survival.
 */
@Service
public class TagService {

    private final TagMapper tagMapper;

    public TagService(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    public RestResponse<List<TagVM>> getAllTags() {
        return RestResponse.getResp("获取成功!", assembleTagVM(tagMapper.getAllTags()));
    }

    private List<TagVM> assembleTagVM(@NotNull List<Tag> allTags) {
        return allTags.stream().map(tag -> new TagVM(tag.getId(), tag.getTitle())).collect(Collectors.toList());
    }

    public ServiceExecuteResult delTagByName(String tagName) {
        tagMapper.deleteTagByName(tagName);
        return ServiceExecuteResult.createFailResult("删除成功！");
    }

    public ServiceExecuteResult createTag(Tag tag) {
        int count = tagMapper.insert(tag);
        return ServiceExecuteResult.createCustomResult(count > 0, count > 0 ? "添加成功！" : "添加失败！");
    }
}
