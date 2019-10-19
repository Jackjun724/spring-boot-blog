package com.jacknoob.blog.service;

import com.jacknoob.blog.mapper.PvMapper;
import com.jacknoob.blog.web.response.RestResponse;
import com.jacknoob.blog.web.util.BaseSpringCommonUtilMethods;
import com.jacknoob.blog.web.vm.DashBoardVM;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    private final PvMapper pvMapper;

    private final BaseSpringCommonUtilMethods commonMethods;

    private final RestTemplate restTemplate;

    public ManageService(PvMapper pvMapper, BaseSpringCommonUtilMethods baseSpringCommonUtilMethods, RestTemplate restTemplate) {
        this.pvMapper = pvMapper;
        this.commonMethods = baseSpringCommonUtilMethods;
        this.restTemplate = restTemplate;
    }

    public RestResponse<DashBoardVM> getDashboardData() {
        List<Map<String, Object>> weekVisitsList = pvMapper.getWeekVisits();
        Integer[] weekVisits = new Integer[7];
        //处理数据  组成周一 -- 周日的数组数据  周为国外日期
        for (Map<String, Object> item : weekVisitsList) {
            int index = Integer.parseInt(item.get("weekd").toString()) - 1;
            weekVisits[index == -1 ? 6 : index] = Integer.parseInt(String.valueOf(item.get("count")));
        }

        /**
         * TODO 启动参数监测
         * RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
         * List<String> aList = bean.getInputArguments();
         *
         * for (int i = 0; i < aList.size(); i++) {
         *     System.out.println( aList.get( i ) );
         * }
         */

        return RestResponse.getResp("成功", assembleDashboardVM(
                weekVisits,
                pvMapper.getDayVisitsNum(),
                pvMapper.getWeekVisitsNum()));
    }

    @SuppressWarnings("unchecked")
    private List<Map<String,Object>> getReplyOfRecent() {
        HttpEntity requestEntity = commonMethods.getHttpRequestEntity();
        //TODO Rest 获取数据
        ResponseEntity responseEntity = restTemplate.exchange("", HttpMethod.GET, requestEntity, Map.class);
        Map result = (Map) responseEntity.getBody();
        if (result == null) {
            return new ArrayList<>(1);
        }
        return (List<Map<String, Object>>) result.get("results");
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
