package com.epoch.dentsumcgb.util.smb;

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


    private String localPath;
    private String remoteUrl;

    private static final String BACKUP = "backup/";

    public Smb(String remoteUrl, String localPath) {
        this.remoteUrl = remoteUrl;
        this.localPath = localPath;
    }

    private SmbFile loginSmb(String remoteUrl) throws MalformedURLException {
        //登录方式 "smb://用户名:密码@IP地址/目录/文件名";
        log.debug("共享文件地址: {}", remoteUrl);

        return new SmbFile(remoteUrl);
        //  remoteFile.connect();
    }

    private void closeSmb(SmbFile... remoteFiles) {
        for (SmbFile remoteFile : remoteFiles) {
            if (remoteFile != null) {
                remoteFile.close();
            }
        }
    }

    private void remoteMove(SmbFile remoteFile) throws MalformedURLException, SmbException {
        if (remoteFile != null) {
            //不适用 File.separator
            SmbFile newdir = loginSmb(remoteUrl + BACKUP + DateUtil.getFormatDate() + "/");
            if (!newdir.exists()) {
                newdir.mkdirs();
            }
            SmbFile newFile = loginSmb(newdir + remoteFile.getName());
            log.info("将要移动到新文件: {}", newFile);
            remoteFile.copyTo(newFile);
            remoteFile.delete();
            closeSmb(newFile, newdir, remoteFile);
        }
    }

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
