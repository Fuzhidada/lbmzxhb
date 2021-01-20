package com.epoch.dentsumcgb.biz;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.config.insert.CommonInsert;
import com.epoch.dentsumcgb.config.smb.Smb;
import com.epoch.dentsumcgb.entity.BaseData;
import com.epoch.dentsumcgb.entity.TSysRecordlist;
import com.epoch.dentsumcgb.mapper.TSysRecordlistMapper;
import com.epoch.dentsumcgb.util.BeanUtil;
import com.xxl.job.core.context.XxlJobHelper;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Service
@Slf4j
public class ReadAndInsertBiz {
    @Resource
    private CommonInsert commonInsert;

    @Resource
    private TSysRecordlistMapper recordlistMapper;

    /**
     * @param file   读取文件
     * @param clz    实体entity.class
     * @param mapper 对应的mapper
     * @param map    文件表头与实体的对应关系
     */
    public Integer readAndInsert(SmbFile file, Class<? extends BaseData> clz,
                                 BizMapper<? extends BaseData> mapper,
                                 Map<String, String> map,
                                 String sysName,
                                 Smb smb) {
        XxlJobHelper.log("任务开始执行 {}", file.getName());

        ArrayList<BaseData> list = new ArrayList<>();
        Integer result = 0;
        String message = "";
        try (SmbFileInputStream in = new SmbFileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        ) {
            String line;
            int index = 0;
            String[] title = new String[0];
            String[] data;
            while ((line = reader.readLine()) != null) {
                // 按tab建分割
                data = line.split("\t");
                if (index == 0) {
                    title = data;
                } else {
                    BaseData o = BeanUtil.revertInputData(title, data, clz, map);
                    list.add(o);
                }
                index++;
            }
            if (!list.isEmpty()) {
                result = commonInsert.setDB(list, mapper);
            }
        } catch (IllegalAccessException e) {
            message += "未能获取该属性" + ExceptionUtils.getStackTrace(e);
            XxlJobHelper.log("未能获取该属性{} ", ExceptionUtils.getStackTrace(e));
        } catch (NoSuchFieldException e) {
            XxlJobHelper.log("未找到该属性 {}", ExceptionUtils.getStackTrace(e));
            message += "未找到该属性" + ExceptionUtils.getStackTrace(e);
        } catch (InvocationTargetException e) {
            XxlJobHelper.log("set方法时异常 {}", ExceptionUtils.getStackTrace(e));
            message += "set方法时异常" + ExceptionUtils.getStackTrace(e);
        } catch (InstantiationException e) {
            XxlJobHelper.log("未能初始化对象 {}", ExceptionUtils.getStackTrace(e));
            message += "未能初始化对象" + ExceptionUtils.getStackTrace(e);
        } catch (IOException e) {
            XxlJobHelper.log("文件读取异常 {}", ExceptionUtils.getStackTrace(e));
            message += "文件读取异常" + ExceptionUtils.getStackTrace(e);
        } catch (SQLException e) {
            XxlJobHelper.log("执行插入语句发生了异常 {}", ExceptionUtils.getStackTrace(e));
            message += "执行插入语句发生了异常" + ExceptionUtils.getStackTrace(e);
        } catch (Exception e) {
            XxlJobHelper.log("未知异常 {}", ExceptionUtils.getStackTrace(e));
            message += "未知异常" + ExceptionUtils.getStackTrace(e);
        }

        if (result > 0) {
            try {
                smb.remoteMove(file);
            } catch (MalformedURLException | SmbException e) {
                XxlJobHelper.log("移动文件夹时失败 {}", ExceptionUtils.getStackTrace(e));
                message += "移动文件夹时失败" + ExceptionUtils.getStackTrace(e);
            }
        } else {
            message += "读到的文件数量为 0，请检查" + file.getName();
            XxlJobHelper.log("读到的文件数量为 0，请检查 {}", file.getName());
        }

        log.info("任务结束{}", file.getName());
        XxlJobHelper.log("任务结束 文件名称:{} 数据量:{}", file.getName(), result);

        //记录表
        TSysRecordlist sysRecordlist = new TSysRecordlist(sysName, file.getName(), result);
        if (!message.equals("")) {
            sysRecordlist.setMsg(message);
        }
        recordlistMapper.insert(sysRecordlist);

        return result;
    }

}
