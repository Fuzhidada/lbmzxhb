package com.epoch.dentsumcgb.util.ftp;

import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
public class FtpUtil {

    @Value("${}")
    private String ip;

    @Value("${}")
    private String userName;

    @Value("${}")
    private String passWord;

    public static boolean login() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("192.168.221.11", 21);
            return ftpClient.login("", "");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ftp login fail :{}", e.getMessage());
            XxlJobHelper.handleResult(500,"ftp登录失败"+e.getMessage());
        }
        return false;
    }

    public static void logout(FTPClient ftpClient) {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("ftp logout fail :{}", e.getMessage());
            XxlJobHelper.handleResult(500,"ftp登录失败"+e.getMessage());
        }
    }

}
