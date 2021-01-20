package com.epoch.dentsumcgb.config.smb;

import com.epoch.dentsumcgb.util.DateUtil;
import com.xxl.job.core.context.XxlJobHelper;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;


import java.io.*;
import java.net.MalformedURLException;
import java.util.concurrent.*;

/**
 * 将远程共享文件下载到本地服务器
 */
@Slf4j
public class Smb implements Callable<Boolean> {

    private static final String A = "-";
    private static final String B = ".";

    private String localPath;

    private String backupPath;
    private String remoteUrl;

    public Smb(String remoteUrl, String backupPath) {
        this.remoteUrl = remoteUrl;
        this.backupPath = backupPath;
    }

    private SmbFile loginSmb(String remoteUrl) throws MalformedURLException {
        //登录方式 "smb://用户名:密码@IP地址/目录/文件名";
        log.debug("共享文件地址: {}", remoteUrl);

        return new SmbFile(remoteUrl);
        //  remoteFile.connect();
    }

    public SmbFile getSmbFile() throws MalformedURLException {
        return loginSmb(remoteUrl);
    }

    public void closeSmb(SmbFile... remoteFiles) {
        for (SmbFile remoteFile : remoteFiles) {
            if (remoteFile != null) {
                remoteFile.close();
            }
        }
    }

    public void remoteMove(SmbFile remoteFile) throws MalformedURLException, SmbException {
        if (remoteFile != null) {
            //不适用 File.separator
            SmbFile newdir = loginSmb(backupPath + DateUtil.getFormatDate() + "/");
            if (!newdir.exists()) {
                newdir.mkdirs();
            }
            String name = remoteFile.getName();
            String[] fileSplit = name.split("\\.");

            SmbFile newFile = loginSmb(newdir + fileSplit[0] + A + DateUtil.getFormatDateHMS() + B + fileSplit[1]);
            XxlJobHelper.log("将要移动到新文件 {}", newFile);
            //@TODO 测试先注释了 ,上线在打开
//            remoteFile.copyTo(newFile);
//            remoteFile.delete();
            closeSmb(newFile, newdir, remoteFile);
        }
    }

    @Deprecated
    private Boolean download(SmbFile smbFile) throws IOException {
        if (smbFile.isDirectory()) {
            //进入到设定的目录
            SmbFile[] files = smbFile.listFiles();
            //生成本地文件夹
            createLocalDir();
            for (SmbFile file : files) {
                if (file.isFile()) {
                    File localFile = new File(localPath + file.getName());
                    try (
                            InputStream in = new BufferedInputStream(new SmbFileInputStream(file));
                            OutputStream out = new BufferedOutputStream(new FileOutputStream(localFile))
                    ) {
                        //读取文件内容
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                            out.write(buffer, 0, len);
                        }
                        out.flush();
                        log.info("下载文件" + remoteUrl + file.getName() + "完成");
                        //移动到备份文件
                        remoteMove(file);
                    } catch (IOException e) {
                        XxlJobHelper.handleResult(500, "从共享文件服务器中下载失败： " + ExceptionUtils.getStackTrace(e));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 生成本地文件夹
     */
    private void createLocalDir() {
        File localDir = new File(localPath);
        if (!localDir.exists()) {
            localDir.mkdirs();
        }
    }

    @Override
    public Boolean call() {
        SmbFile smbFile365;
        boolean result;
        try {
            smbFile365 = loginSmb(remoteUrl);
            result = download(smbFile365);
            closeSmb(smbFile365);
        } catch (MalformedURLException e) {
            XxlJobHelper.handleResult(500, "共享文件地址有误，请检查" + ExceptionUtils.getStackTrace(e));
            log.error("共享文件地址有误 {}", ExceptionUtils.getStackTrace(e));
            result = false;
        } catch (IOException e) {
            XxlJobHelper.handleResult(500, "共享文件服务连接失败" + ExceptionUtils.getStackTrace(e));
            log.error("共享文件服务连接失败 {}", ExceptionUtils.getStackTrace(e));
            result = false;
        }
        return result;
    }


}
