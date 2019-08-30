package com.jacknoob.blog.service;

import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.mapper.NoteHasTagsMapper;
import com.jacknoob.blog.mapper.NoteMapper;
import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.web.response.Page;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.vm.EditNoteVM;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author JackJun
 * 2019/7/30 17:29
 * Life is just about survival.
 */
@Service
public class NoteService {

    @Inject
    private TagMapper tagMapper;

    @Inject
    private NoteMapper noteMapper;

    @Inject
    private NoteHasTagsMapper noteHasTagsMapper;

    @Transactional(rollbackFor = Exception.class)
    public RestResponse addNote(EditNoteVM vm) {
        tagMapper.insertIgnore(vm.getTags());
        Note note = assemblyNoteFromEditNoteVM(vm);
        noteMapper.insert(note);
        List<Integer> tagsId = tagMapper.selectiveSelect(vm.getTags());
        noteHasTagsMapper.insertList(tagsId, note.getId());
        return RestResponse.getResp("新增成功!");
    }

    @Transactional(rollbackFor = Exception.class)
    public RestResponse delNote(Integer id) {
        noteHasTagsMapper.deleleSelective(id);
        noteMapper.deleteById(id);
        return RestResponse.getResp("删除成功!");
    }

    @Transactional(rollbackFor = Exception.class)
    public RestResponse editNote(EditNoteVM vm) {
        tagMapper.insertIgnore(vm.getTags());
        Note note = assemblyNoteFromEditNoteVM(vm);
        noteMapper.update(note);
        List<Integer> tagsId = tagMapper.selectiveSelect(vm.getTags());
        noteHasTagsMapper.insertList(tagsId, note.getId());
        return RestResponse.getResp("修改成功!");
    }

    private Note assemblyNoteFromEditNoteVM(EditNoteVM vm) {
        return new Note(vm.getId(),
                vm.getTitle()
                , vm.getPublishTime() == null ? new Date(System.currentTimeMillis()) : vm.getPublishTime()
                , vm.getLastUpdateTime() == null ? new Date(System.currentTimeMillis()) : vm.getLastUpdateTime()
                , vm.getContent()
                , vm.getDisplayType()
                , vm.getDisplayContent());
    }

    public Page<List<Note>> noteList(Pageable page, List<String> filters, String orderByColumn, boolean isDesc) {

        return null;
    }
}
