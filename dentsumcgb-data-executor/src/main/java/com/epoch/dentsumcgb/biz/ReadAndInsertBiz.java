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
        XxlJobHelper.handleResult(200, "任务开始执行--");
        log.info("任务开始执行{}", file.getName());

        ArrayList<BaseData> list = new ArrayList<>();
        Integer result = 0;
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
            log.error("未能获取该属性{}", ExceptionUtils.getStackTrace(e));
        } catch (NoSuchFieldException e) {
            log.error("未找到该属性{}", ExceptionUtils.getStackTrace(e));
        } catch (InvocationTargetException e) {
            log.error("set方法时异常{}", ExceptionUtils.getStackTrace(e));
        } catch (InstantiationException e) {
            log.error("未能初始化对象{}", ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            log.error("文件读取异常{}", ExceptionUtils.getStackTrace(e));
            XxlJobHelper.handleResult(500, "文件读取异常" + ExceptionUtils.getStackTrace(e));
        }catch (Exception e){
            log.error("未知异常{}", ExceptionUtils.getStackTrace(e));
            XxlJobHelper.handleResult(500, "未知异常：" + ExceptionUtils.getStackTrace(e));
        }
        TSysRecordlist sysRecordlist = new TSysRecordlist(sysName, file.getName(), result);
        //记录表
        recordlistMapper.insert(sysRecordlist);
        if (result > 0) {
            try {
                smb.remoteMove(file);
                XxlJobHelper.handleResult(200, "读取完成" + sysRecordlist.toString());
            } catch (MalformedURLException | SmbException e) {
                log.error("移动文件夹时失败 {} {}", file.getName(), ExceptionUtils.getStackTrace(e));
            }
        } else {
            XxlJobHelper.handleResult(500, "读取失败" + sysRecordlist.toString());
        }

        XxlJobHelper.handleResult(200, "任务结束--");
        log.info("任务结束{}", file.getName());
        return result;
    }

}
