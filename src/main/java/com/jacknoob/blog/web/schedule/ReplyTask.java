package com.jacknoob.blog.web.schedule;

import com.jacknoob.blog.config.MailServer;
import com.jacknoob.blog.mapper.NoteMapper;
import com.jacknoob.blog.properties.LeanCloud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JackJun
 * 2019/10/6 19:11
 * Life is not just about survival.
 */
@Component
@SuppressWarnings("all")
public class ReplyTask {
    /**
     * 评论通知调度任务.
     * 服务器配置低，暂不存储评论数据，降低压力。
     * 后期需要可考虑Redis
     */
    private static final Logger logger = LoggerFactory.getLogger(ReplyTask.class);

    private final RestTemplate restTemplate;
    private final HttpEntity requestEntity;
    private final NoteMapper noteMapper;
    private final MailServer mailServer;
    private int replyCount = 0;

    public ReplyTask(RestTemplate restTemplate, LeanCloud leanCloud, NoteMapper mapper, MailServer mailServer) {
        this.mailServer = mailServer;
        this.noteMapper = mapper;
        this.restTemplate = restTemplate;
        //构造请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");
        headers.set("X-LC-Id", leanCloud.getAppId());
        headers.set("X-LC-Key", leanCloud.getAppKey());
        //封装请求实体
        requestEntity = new HttpEntity<>(headers);
        //请求
        List<Map> reply = getReply();
        replyCount = reply.size();
    }

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void validReplyData() throws ParseException, MessagingException {
        List<Map> reply = getReply();
        if (reply.size() > replyCount) {
            for (int i = 0; i < reply.size() - replyCount; i++) {
                Map item = reply.get(i);
                int noteId = Integer.parseInt(item.get("url").toString());
                String title = noteMapper.getTitleById(noteId);
                String comment = item.get("comment").toString();
                String nick = item.get("nick").toString();
                String ip = item.get("ip").toString();
                String createAt = item.get("createdAt").toString().replaceAll("[TZ]", " ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0:00"));
                Date temp = dateFormat.parse(createAt);
                dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                mailServer.notify("提示：文章收到新的回复！", "文章 [" + title + "] 收到回复\nComment：" + comment + "\nNick：" + nick + "\nIp：" + ip + "\nTime:" + dateFormat.format(temp));
            }
            replyCount = reply.size();
        }
    }

    private List<Map> getReply() {
        try {
            HttpEntity<Map> response = restTemplate.exchange("https://gyf9w7cc.api.lncld.net/1.1/cloudQuery?cql=select * from Comment order by createdAt desc", HttpMethod.GET, requestEntity, Map.class);
            Map result = response.getBody();
            List<Map> reply = (List) result.get("results");
            return reply;
        } catch (HttpClientErrorException exception) {
            logger.error(exception.toString());
        }
        return new ArrayList<>();
    }
}
