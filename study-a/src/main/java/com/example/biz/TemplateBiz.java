package com.example.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class TemplateBiz {
    @Autowired
    private RestTemplate restTemplate;

    public boolean sendPost() {
        Object ss = restTemplate.exchange("http://10.133.247.39:18081/scms/lh/inf/saveIndirectSettleLH", HttpMethod.POST, setEntity(), String.class);
        return true;
    }

    private HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private HttpEntity<String> setEntity() {
        String body = "{\"indirectmain\":{\"comid\":\"8633\",\"batchno\":\"HIM40201909200000000000000156\",\"comcode\":\"86330100\",\"totalcount\":\"1\",\"subcompany\":\"health\"},\"indirectinfolist\":[{\"indirectinfomain\":{\"sumcostfee\":\"180.30\",\"indirectagentcode\":\"101\",\"settleno\":\"HII40201909200000000000000154\",\"settletype\":\"Y\",\"salemancode\":\"2194131211\",\"indirectagentname\":\"个人代理\",\"salemanname\":\"柳冬云\"},\"indirectdetaillist\":[{\"allowpay\":\"1\",\"costfee\":\"180.30\",\"indirecttype\":\"001\",\"salemancode\":\"续期服务奖\",\"indirectname\":\"22\",\"indirectcode\":\"231\"}]}]}";
        return new HttpEntity<>(body, setHeader());
    }
}
