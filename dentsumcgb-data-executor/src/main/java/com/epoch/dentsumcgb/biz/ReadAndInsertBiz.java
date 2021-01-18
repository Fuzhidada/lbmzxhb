package com.epoch.dentsumcgb.biz;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.config.insert.CommonInsert;
import com.epoch.dentsumcgb.entity.BaseData;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.mapper.THypFinDataMapper;
import com.epoch.dentsumcgb.util.BeanUtil;
import com.xxl.job.core.context.XxlJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

@Service
@Slf4j
public class ReadAndInsertBiz {
    @Resource
    private CommonInsert commonInsert;

    /**
     * @param file   读取文件
     * @param clz    实体entity.class
     * @param mapper 对应的mapper
     * @param map    文件表头与实体的对应关系
     */
    public void readAndInsert(File file, Class<? extends BaseData> clz, BizMapper<? extends BaseData> mapper, Map<String, String> map) {
        ArrayList<BaseData> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
                commonInsert.setDB(list, mapper);
            }
        } catch (IOException e) {
            log.error("文件读取异常{}", ExceptionUtils.getStackTrace(e));
            XxlJobHelper.handleResult(500, "文件读取异常" + ExceptionUtils.getStackTrace(e));
        } catch (IllegalAccessException e) {
            log.error("未能获取该属性{}", ExceptionUtils.getStackTrace(e));
        } catch (NoSuchFieldException e) {
            log.error("未找到该属性{}", ExceptionUtils.getStackTrace(e));
        } catch (InvocationTargetException e) {
            log.error("set方法时异常{}", ExceptionUtils.getStackTrace(e));
        } catch (InstantiationException e) {
            log.error("未能初始化对象{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
