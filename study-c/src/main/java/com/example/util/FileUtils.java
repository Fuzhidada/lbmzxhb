package com.example.util;


import java.io.*;

public class FileUtils {
    public static void readFile(String absFileName) {

        File f = new File(absFileName);
        File outF = new File(absFileName + "_new.txt");
        if (!f.exists()) {
            throw new IllegalArgumentException("文件不存在");
        }

        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outF))
        ) {

            int len;
            byte[] bytes = new byte[1024];
            int i = 0;
            while ((len = in.read(bytes, 0, bytes.length)) != -1) {
                out.write(bytes, 0, len);
                if (i++ == 1) {
                    System.out.println("T" + f.delete());
                    Thread.sleep(2000000);
                }

                System.out.println(i);
                //在close的时候会自动的flush
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            FileUtils.readFile("D:\\ideawork\\test\\word.txt");
        }).start();

        new Thread(() -> {
            File f = new File("D:\\ideawork\\test\\word.txt");
            System.out.println("我" + f.delete());
        }).start();

        //测试发现在文件的时流未关闭的时候调用删除功能  删除不会成功

    }


}
