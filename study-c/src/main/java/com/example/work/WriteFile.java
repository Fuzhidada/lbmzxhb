package com.example.work;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

@Slf4j
public class WriteFile implements Runnable {
    private ArrayList<String> titleList;
    private File file;
    private FileOutputStream out = null;
    private OutputStreamWriter osw = null;
    private BufferedWriter bw = null;

    WriteFile(ArrayList<String> titleList) {
        this.titleList = titleList;
        try {
            //创建文件夹
            createFile();
            //文件写入字节流
            out = new FileOutputStream(file);
            //文件写入字符流
            osw = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            //文件写入缓冲流
            bw = new BufferedWriter(osw);
            //创建表头
            //createTitle();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件创建异常");
        }
    }
    //创建文件命名以表名
    private void createFile() throws IOException {
        file = new File("D://pickData/tt6/" + titleList.get(0) + ".txt");
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        file.createNewFile();
        // log.info("  开始创建文件: {}", titleList.get(0));
    }

    public void createTitle() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 1; i < titleList.size(); i++) {
            buffer.append(titleList.get(i));
            buffer.append(",");
        }
        //去掉最后一个逗号
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("\r");
        serialize(bw, buffer);
    }

    public void close() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (osw != null) {
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
//        createTitle();
//        writeContent(Lists.newArrayList());
    }
    //将数据写入文件
    public void writeContent(ArrayList<String[]> dataList) {
        StringBuffer buffer;
        try {
            boolean flag = true;
            while (flag) {
                buffer = new StringBuffer();
                for (String[] data : dataList) {
                    for (String datum : data) {
                        //
                        split(buffer, datum);
                    }
                    //去掉最后一个逗号
                    buffer.deleteCharAt(buffer.length() - 1);
                    //换行
                    buffer.append("\r");
                }
                //写入处理好的数据到文件
                serialize(bw, buffer);
                //置空结束循环
                dataList = null;
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //写入
    private static void serialize(BufferedWriter bw, StringBuffer buffer) {
        try {
            //写入
            bw.write(buffer.toString());
            //刷新防止堵塞
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //拼接
    private static void split(StringBuffer buffer, String value) {
        //buffer.append("\"");
        if ("null".equals(value) || null == value) {
            buffer.append("");
        } else {
            buffer.append(value);
        }

        //buffer.append("\"");
        //buffer.append(",");
        buffer.append("\t");
    }
}
