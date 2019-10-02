package com.jacknoob.blog.service;

import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.mapper.NoteHasTagsMapper;
import com.jacknoob.blog.mapper.NoteMapper;
import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.web.response.Page;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.vm.EditNoteVM;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JackJun
 * 2019/7/30 17:29
 * Life is just about survival.
 */
@Service
public class NoteService {

    private static final String PUBLISH_TIME = "publish_time";
    private static final String PUBLISH_TIME_ALIAS = "publishtime";
    private static final String LAST_UPDATE_TIME = "last_update_time";
    private static final String LAST_UPDATE_TIME_ALIAS = "lastupdatetime";
    private final TagMapper tagMapper;
    private final NoteMapper noteMapper;
    private final NoteHasTagsMapper noteHasTagsMapper;

    public NoteService(TagMapper tagMapper, NoteMapper noteMapper, NoteHasTagsMapper noteHasTagsMapper) {
        this.tagMapper = tagMapper;
        this.noteMapper = noteMapper;
        this.noteHasTagsMapper = noteHasTagsMapper;
    }


    @Transactional(rollbackFor = Exception.class)
    public RestResponse addNote(@NotNull EditNoteVM vm) {
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
    public RestResponse editNote(@NotNull EditNoteVM vm) {
        tagMapper.insertIgnore(vm.getTags());
        Note note = assemblyNoteFromEditNoteVM(vm);
        noteMapper.update(note);
        List<Integer> tagsId = tagMapper.selectiveSelect(vm.getTags());
        noteHasTagsMapper.insertList(tagsId, note.getId());
        return RestResponse.getResp("修改成功!");
    }

    public Page<Note> noteList(Pageable page, List<String> filters, String orderByColumn, boolean isDesc) {
        //防止恶意注入
        filters = filters.stream().map(s -> s.replace("-","\\-").replace("'","\\'")).collect(Collectors.toList());
        //处理排序列
        if (PUBLISH_TIME_ALIAS.equalsIgnoreCase(orderByColumn)) {
            orderByColumn = PUBLISH_TIME;
        } else if (LAST_UPDATE_TIME_ALIAS.equalsIgnoreCase(orderByColumn)) {
            orderByColumn = LAST_UPDATE_TIME;
        }
        return assembleNoteListVM(
                noteMapper.selectAll(filters, orderByColumn, isDesc, (page.getPageNumber() - 1) * page.getPageSize(), page.getPageSize()),
                noteMapper.countBy(filters),
                page.getPageSize(),
                page.getPageNumber()
        );
    }


    public Note getNoteById(Integer id) {
        return noteMapper.getNoteById(id);
    }

    private Note assemblyNoteFromEditNoteVM(@NotNull EditNoteVM vm) {
        return new Note(vm.getId(),
                vm.getTitle()
                , vm.getPublishTime() == null ? new Date(System.currentTimeMillis()) : vm.getPublishTime()
                , vm.getLastUpdateTime() == null ? new Date(System.currentTimeMillis()) : vm.getLastUpdateTime()
                , vm.getContent()
                , vm.getDisplayType()
                , vm.getDisplayContent()
                , vm.getHtml());
    }

    @NotNull
    private Page<Note> assembleNoteListVM(List<Note> tableData, int count, int pageSize, int pageNum) {
        Page<Note> page = new Page<>();
        page.setTotal(count);
        page.setPageSize(pageSize);
        page.setPage(pageNum);
        page.setData(tableData);
        return page;
    }
}
