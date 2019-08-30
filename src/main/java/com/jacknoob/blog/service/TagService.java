package com.jacknoob.blog.service;

import com.jacknoob.blog.entity.Tag;
import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.vm.TagVM;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JackJun
 * 2019/7/30 16:37
 * Life is just about survival.
 */
@Service
public class TagService {

    @Inject
    private TagMapper tagMapper;

    public RestResponse<List<TagVM>> getAllTags() {
        return RestResponse.getResp("获取成功!", assembleTagVM(tagMapper.getAllTags()));
    }

    private List<TagVM> assembleTagVM(List<Tag> allTags) {
        return allTags.stream().map(tag -> new TagVM(tag.getId(), tag.getTitle())).collect(Collectors.toList());
    }
}
