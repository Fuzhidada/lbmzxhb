package com.epoch.dentsumcgb.util.smb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
@Slf4j
public class SmbConfig {

    @Value("${smb.ip}")
    private String ip;

    @Value("${smb.userName}")
    private String userName;

    @Value("${smb.passWord}")
    private String passWord;

    @Value("${smb.sharePath}")
    private String sharePath;

    @Value("${smb.d365}")
    private String d365;

    @Value("${smb.hyp}")
    private String hyp;

    @Value("${smb.localPath}")
    private String localPath;

    @Bean("d365")
    @Lazy
    Smb d365() {
        String remoteUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + d365;
        log.info("d365共享文件地址: {}", remoteUrl);
        return new Smb(remoteUrl, localPath + d365);
    }

    @Bean("hyp")
    @Lazy
    Smb hyp() {
        String remoteUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + hyp;
        log.info("hyp共享文件地址: {}", remoteUrl);
        return new Smb(remoteUrl, localPath + hyp);
    }
}
