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

import javax.inject.Inject;
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

    @Inject
    private TagMapper tagMapper;

    @Inject
    private NoteMapper noteMapper;

    @Inject
    private NoteHasTagsMapper noteHasTagsMapper;

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


    private Note assemblyNoteFromEditNoteVM(@NotNull EditNoteVM vm) {
        return new Note(vm.getId(),
                vm.getTitle()
                , vm.getPublishTime() == null ? new Date(System.currentTimeMillis()) : vm.getPublishTime()
                , vm.getLastUpdateTime() == null ? new Date(System.currentTimeMillis()) : vm.getLastUpdateTime()
                , vm.getContent()
                , vm.getDisplayType()
                , vm.getDisplayContent());
    }

    private static final String PUBLISH_TIME = "publish_time";
    private static final String PUBLISH_TIME_ALIAS = "publishtime";
    private static final String LAST_UPDATE_TIME = "last_update_time";
    private static final String LAST_UPDATE_TIME_ALIAS = "lastupdatetime";

    public Page<List<Note>> noteList(Pageable page, List<String> filters, String orderByColumn, boolean isDesc) {
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
                noteMapper.count(),
                page.getPageSize(),
                page.getPageNumber()
        );
    }

    private Page<List<Note>> assembleNoteListVM(List<Note> tableData, int count, int pageSize, int pageNum) {
//        Page<List<Note>> noteListVO = new PageVO<Note>();
//        noteListVO.setCount(count);
//        noteListVO.setPageNum(pageNum);
//        noteListVO.setPageSize(pageSize);
//        noteListVO.setTableData(tableData);
//        return noteListVO;
        return null;
    }
}
