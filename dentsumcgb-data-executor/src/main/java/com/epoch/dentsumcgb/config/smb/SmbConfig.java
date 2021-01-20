package com.epoch.dentsumcgb.config.smb;

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
    @Value("${smb.epayroll}")
    private String epayroll;


    @Value("${smb.backupPath}")
    private String backupPath;


    @Value("${smb.local.path}")
    private String localPath;

    @Bean("d365")
    @Lazy
    Smb d365() {
        String remoteUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + d365;
        String backupUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + backupPath + d365;
        return new Smb(remoteUrl, backupUrl);
    }

    @Bean("hyp")
    @Lazy
    Smb hyp() {
        String remoteUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + hyp;
        String backupUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + backupPath + hyp;
        return new Smb(remoteUrl, backupUrl);
    }

    @Bean("epayroll")
    @Lazy
    Smb epayroll() {
        String remoteUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + epayroll;
        String backupUrl = "smb://" + userName + ":" + passWord + "@" + ip + sharePath + backupPath + epayroll;
        return new Smb(remoteUrl, backupUrl);
    }
}
