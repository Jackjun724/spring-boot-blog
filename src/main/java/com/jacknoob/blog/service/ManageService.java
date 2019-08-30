package com.jacknoob.blog.service;

import com.jacknoob.blog.entity.Reply;
import com.jacknoob.blog.mapper.PvMapper;
import com.jacknoob.blog.mapper.ReplyMapper;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.vm.DashBoardVM;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author JackJun
 * 2019/7/30 16:08
 * Life is just about survival.
 */
@Service
public class ManageService {
    @Inject
    private ReplyMapper replyMapper;

    @Inject
    private PvMapper pvMapper;

    public RestResponse<DashBoardVM> getDashboardData() {
        List<Map<String, Object>> weekVisitsList = pvMapper.getWeekVisits();
        int[] weekVisits = new int[7];
        //处理数据  组成周一 -- 周日的数组数据  周为国外日期
        for (Map<String, Object> item : weekVisitsList) {
            int index = Integer.parseInt(item.get("weekd").toString()) - 1;
            weekVisits[index == -1 ? 6 : index] = Integer.parseInt(String.valueOf(item.get("count")));
        }

        List<Map<String, Object>> replyList = replyMapper.getWeekDayReplyCount();
        int[] reply = new int[7];
        //处理数据  组成周一 -- 周日的数组数据  周为国外日期
        for (Map<String, Object> aResult : replyList) {
            int index = Integer.parseInt(aResult.get("weekd").toString()) - 1;
            reply[index == -1 ? 6 : index] = Integer.parseInt(aResult.get("count").toString());
        }

        return RestResponse.getResp("成功", assembleDashboardVM(
                replyMapper.getTop6Reply(),
                reply,
                weekVisits,
                pvMapper.getDayVisitsNum(),
                pvMapper.getWeekVisitsNum(),
                replyMapper.getReplyCountByDay()));
    }

    @SuppressWarnings("all")
    private DashBoardVM assembleDashboardVM(List<Reply> replyList, int[] weekReply, int[] weekVisits, int dayVisitsNum, int weekVisitsNum, int replyNum) {
        DashBoardVM vm = new DashBoardVM();
        vm.setReply(replyList);
        vm.getCharts().setWeekReply(new ArrayList(Arrays.asList(weekReply)));
        vm.getCharts().setWeekVisits(new ArrayList(Arrays.asList(weekVisits)));
        vm.setDayVisitsNum(dayVisitsNum);
        vm.setWeekVisitsNum(weekVisitsNum);
        vm.setReplyNum(replyNum);
        return vm;
    }
}
