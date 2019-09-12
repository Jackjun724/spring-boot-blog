package com.jacknoob.blog.service;

import com.jacknoob.blog.mapper.PvMapper;
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
    private PvMapper pvMapper;

    public RestResponse<DashBoardVM> getDashboardData() {
        List<Map<String, Object>> weekVisitsList = pvMapper.getWeekVisits();
        Integer[] weekVisits = new Integer[7];
        //处理数据  组成周一 -- 周日的数组数据  周为国外日期
        for (Map<String, Object> item : weekVisitsList) {
            int index = Integer.parseInt(item.get("weekd").toString()) - 1;
            weekVisits[index == -1 ? 6 : index] = Integer.parseInt(String.valueOf(item.get("count")));
        }

        return RestResponse.getResp("成功", assembleDashboardVM(
                weekVisits,
                pvMapper.getDayVisitsNum(),
                pvMapper.getWeekVisitsNum()));
    }

    @SuppressWarnings("all")
    private DashBoardVM assembleDashboardVM(Integer[] weekVisits, int dayVisitsNum, int weekVisitsNum) {
        DashBoardVM vm = new DashBoardVM();
        vm.getCharts().setWeekVisits(new ArrayList<Integer>(Arrays.asList(weekVisits)));
        vm.setDayVisitsNum(dayVisitsNum);
        vm.setWeekVisitsNum(weekVisitsNum);
        return vm;
    }
}
