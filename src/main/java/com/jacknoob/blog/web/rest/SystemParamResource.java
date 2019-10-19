package com.jacknoob.blog.web.rest;

import com.jacknoob.blog.web.response.RestResponse;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author JackJun
 * @date 2019/10/19 6:38 下午
 */
@RestController
@RequestMapping("/api/system")
public class SystemParamResource {

    private final RestTemplate restTemplate;

    public SystemParamResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/unknown")
    @Timed
    @ApiOperation("模拟Http接口测试工具")
    @SuppressWarnings("all")
    public ResponseEntity requestUrl(@RequestParam Integer id,@RequestParam Double price) throws URISyntaxException, UnsupportedEncodingException {
        //构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        //封装请求实体
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("dev_no", "866262041626622");
        map.add("cart_list[0][account]","13929530368");
        map.add("cart_list[0][cid]","115673");
        map.add("cart_list[0][dev_no]","866262041626622");
        map.add("cart_list[0][sev_no]","A");
        map.add("cart_list[0][row]","3");
        map.add("cart_list[0][col]","3");
        map.add("cart_list[0][goods_id]",id.toString());
        map.add("cart_list[0][cat_id]","0");
        map.add("cart_list[0][pic_url]","https://yun.yishouyun.cn/Uploads/Images/2019/09/02/20190902215758_6351.jpeg");
        map.add("cart_list[0][is_active]","0");
        map.add("cart_list[0][price]",price.toString());
        map.add("cart_list[0][goods_name]","农夫山泉");
        map.add("cart_list[0][stock]","5");
        map.add("cart_list[0][qty]","1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        Map<String,String> result = (Map<String, String>) restTemplate.postForEntity(new URI("https://yun.yishouyun.cn/wechat/order/create"),request, Map.class).getBody();
        String url = result.get("url");
        if (url == null){
            return ResponseEntity.badRequest().body(RestResponse.getResp("获取失败！"));
        }

        return ResponseEntity.ok(RestResponse.getResp("获取成功！", "alipays://platformapi/startapp?appId=20000067&url="+URLEncoder.encode("https://yun.yishouyun.cn"+url,"UTF-8")));
    }
}
