package com.example.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio 复制文件
 */

public class NioUtil {

    private static void fion(String absFileName) {

        try (FileInputStream in = new FileInputStream(new File(absFileName));
             FileOutputStream out = new FileOutputStream(new File(absFileName + "xx.txt"));
             FileChannel channel = in.getChannel();
             FileChannel outFile = out.getChannel();
        ) {

            int len;
            long size = channel.size();
            ByteBuffer bit = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

//            System.out.println(new String(bit.array(), 0, (int) size));

            outFile.write(bit);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        new Thread(() -> {
            NioUtil.fion("D:\\ideawork\\test\\word.txt");
        }).start();

    }


}
