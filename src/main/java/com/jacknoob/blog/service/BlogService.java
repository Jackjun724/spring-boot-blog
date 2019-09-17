package com.jacknoob.blog.service;

import com.github.pagehelper.PageHelper;
import com.jacknoob.blog.entity.Note;
import com.jacknoob.blog.entity.Tag;
import com.jacknoob.blog.mapper.NoteClickMapper;
import com.jacknoob.blog.mapper.NoteMapper;
import com.jacknoob.blog.mapper.PvMapper;
import com.jacknoob.blog.mapper.TagMapper;
import com.jacknoob.blog.web.response.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author JackJun
 * 2019/7/18 9:26
 * Life is just about survival.
 */
@Service
public class BlogService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private PvMapper pvMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private NoteClickMapper noteClickMapper;

    public List<Map<String, Object>> getNoteListByPage(Page<Map<String, Object>> pageVO, int pageSize, Integer pageNum) {
        pageVO.setPageSize(pageSize);
        pageVO.setPage(pageNum);
        pageVO.setTotal(noteMapper.enableCount());

        //获取置顶文章总行数
        int enableTopCount = noteMapper.enableTopCount();

        //判断当前页是否还有置顶文章
        int count = enableTopCount - pageSize * (pageNum - 1);

        if (count > 0) {
            if (count < pageSize) {
                PageHelper.offsetPage(pageSize * (pageNum - 1), count);
                List<Map<String, Object>> topResult = noteMapper.enableTopList();
                PageHelper.offsetPage(pageSize * (pageNum - 1), pageSize - topResult.size());
                List<Map<String, Object>> normalResult = noteMapper.enableList();
                topResult.addAll(normalResult);
                return topResult;
            }
            PageHelper.offsetPage(pageSize * (pageNum - 1), pageSize);
            return noteMapper.enableTopList();
        }

        // 否则直接查十篇正常文章返回
        PageHelper.offsetPage(pageSize * (pageNum - 1) - enableTopCount, pageSize);
        return noteMapper.enableList();
    }

    /**
     * 根据文章ID判断Redis缓存命中 , 在 修改文章的时候更新缓存
     * //TODO Redis
     * @param id        文章ID
     * @return Entity
     */
//    @Cacheable("note")
    public Note getNoteByIdAndLastUpdateTimestamp(Integer id) {
        return noteMapper.getNoteById(id);
    }

    public void pv() {
        List<Map<String, Object>> list = pvMapper.selectNow();
        if (list.size() == 0) {
            pvMapper.insertNow();
        } else {
            pvMapper.addOneNow();
        }
    }

    public List<Map<String, Object>> loadMoreByPage(int pageNum, Page page) {
        page.setPage(pageNum);
        page.setTotal(noteMapper.enableCount());
        PageHelper.startPage(page.getPage(), page.getPageSize());
        return noteMapper.loadMore();
    }

    public List<Tag> findAllTags() {
        return tagMapper.getAllTags();
    }

    public void noteClick(Integer id) {
        noteClickMapper.insertClick(id);
    }

    public int getClickNum(Integer id) {
        return noteClickMapper.clickNum(id);
    }

    public List<Note> getNoteByTagId(Integer id) {
        return noteMapper.getNoteByTagId(id);
    }

    public String getTagNameById(int id) {
        return tagMapper.getTagNameById(id);
    }
}
