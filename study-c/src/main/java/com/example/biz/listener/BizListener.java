package com.example.biz.listener;

import com.example.entity.Fuzhi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BizListener implements ApplicationListener<BizEvent> {

    @Override
    public void onApplicationEvent(BizEvent applicationEvent) {
        Object t = applicationEvent.getSource();
        String operate = applicationEvent.getOperate();
        if (t instanceof Fuzhi) {
            log.info("BIZ执行完了 FUZHI " + operate + " 接下来需要操作啥 <-->  ");
        } else {
            log.info("sth others--");
        }

    }
}
