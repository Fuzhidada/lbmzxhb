package com.epoch.dentsumcgb.job;

import com.epoch.dentsumcgb.config.BizMapper;
import com.epoch.dentsumcgb.entity.THypFinData;
import com.epoch.dentsumcgb.mapper.THypFinDataMapper;
import com.epoch.dentsumcgb.util.BeanUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Slf4j
public class HYPTask {
    @Value("${smb.localPath}")
    private String localPath;
    @Value("${smb.hyp}")
    private String hyp;

    @Resource
    private THypFinDataMapper hypFinDataMapper;

    public static final int skipSize = 1000;

    @XxlJob(value = "HYPTask")
    public void start() throws Exception {
        XxlJobHelper.handleResult(200, "xxxxxx");
        System.out.println("任务--->");
    }

    public void dealFinData() {
        File localParentDir = new File(localPath + hyp);
        File[] files = localParentDir.listFiles();

        assert files != null;
        Stream.of(files).forEach(file -> {
            String name = file.getName();
            if (name.contains("HYP_FIN_DATA")) {
                ArrayList<THypFinData> list = new ArrayList<>();
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
                            THypFinData finData = new THypFinData();
                            BeanUtil.revertInputData(title, data, finData, THypFinData.getMapping());
                            list.add(finData);
                        }
                        index++;
                    }
                    if (!list.isEmpty()) {
                        setDB(list, hypFinDataMapper);
                    }
                } catch (IOException e) {
                    log.error("文件读取异常{}", ExceptionUtils.getStackTrace(e));
                } catch (IllegalAccessException e) {
                    log.error("未能获取该属性{}", ExceptionUtils.getStackTrace(e));
                } catch (NoSuchFieldException e) {
                    log.error("未找到该属性{}", ExceptionUtils.getStackTrace(e));
                } catch (InvocationTargetException e) {
                    log.error("set方法时异常{}", ExceptionUtils.getStackTrace(e));
                }
            }
        });

    }

    private void setDB(ArrayList list, BizMapper mapper) {

        ExecutorService pool = Executors.newFixedThreadPool(4,
                new ThreadFactoryBuilder().setNameFormat("smb-insert-%d").build());
        for (int i = 0; i < list.size() / skipSize +1; i++) {
            pool.submit(new InsertTask((ArrayList) list.stream().skip(i * skipSize).limit(skipSize).collect(Collectors.toList()), mapper));
        }
    }

    private static class InsertTask implements Callable<Integer> {
        private ArrayList list;
        private BizMapper mapper;

        InsertTask(ArrayList list, BizMapper mapper) {
            this.list = list;
            this.mapper = mapper;
        }

        @Override
        public Integer call() throws Exception {
            log.info("执行数据库插入{}", list.size());
            return mapper.batchInsert(list);
        }
    }

}


    
