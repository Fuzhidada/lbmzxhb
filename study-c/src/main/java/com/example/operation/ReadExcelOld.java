package com.example.operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import com.example.common.util.ReadExcel;
import com.google.common.util.concurrent.ThreadFactoryBuilder;


/**
 * 读excel生成insert语句
 */
public class ReadExcelOld {


    public static void main(String[] args) {

        try {
//            CopyOnWriteArrayList ;
            FutureTask a = new FutureTask(null);
            a.isDone();
            ThreadFactory namedFa = new ThreadFactoryBuilder().setNameFormat("read-%d").build();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 12,
                    30, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), namedFa, new ThreadPoolExecutor.AbortPolicy());

            final String filePath = "D:\\test\\DRUG\\药店清单.xlsx";

            List<List<String>> list = new ArrayList<>();
            new ReadExcel(filePath).setHandler(new ReadExcel.SimpleSheetContentsHandler() {

                @Override
                public void endRow(int rowNum) {
                    if (rowNum == 0) {
                    } else {
                        list.add(row);
                    }
                }
            }).parse();

            System.out.println("共计：" + list.size());

            int subSize = 10000;
            int start;
            int end;
            for (int k = 0; k <= list.size() / subSize; k++) {
                start = k * subSize;
                end = (k + 1) * subSize;
                if (end > list.size()) {
                    end = list.size();
                }
                Write write = new Write(list.subList(start, end), k);
                executor.submit(write);
            }
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static class Write implements Runnable {
        private List<List<String>> list;
        private int rs;

        public Write(List<List<String>> list, int rs) {
            this.list = list;
            this.rs = rs;
        }

        @Override
        public void run() {
            StringBuffer sql = new StringBuffer("INSERT  INTO LDDRUGSTORE(DRUGSTORECODE,DRUGSTORENAME,FIXFLAG,ADDRESS,BUSISCOPE,operator,MAKEDATE,MAKETIME,MODIFYDATE,MODIFYTIME,MANAGECOM) values \r"
            );
            for (int i = 0; i < list.size(); i++) {
                String DRUGSTORECODE = list.get(i).get(0);
                String DRUGSTORENAME = list.get(i).get(1);

                String FIXFLAG = list.get(i).get(6);
                if ("是".equals(FIXFLAG)) {
                    FIXFLAG = "1";// 是否供应商合作药店
                } else {
                    FIXFLAG = "0";
                }

                String ADDRESS = list.get(i).get(2);
                String BUSISCOPE = list.get(i).get(4);
                sql.append("('" + DRUGSTORECODE + "','" + DRUGSTORENAME + "','" + FIXFLAG + "','" + ADDRESS + "', '" + BUSISCOPE + "',");
                sql.append("'it001',CURRENT_DATE,CURRENT_TIME,CURRENT_DATE,CURRENT_TIME,'86'),").append("\r");
            }
            sql.replace(sql.lastIndexOf(","), sql.length(), ";");

            serialized(sql, rs);
        }

        void serialized(StringBuffer sql, int i) {
            try {
                File f = new File("D:/test/DRUG/sql/fuzhi_药店配置_" + i + ".sql");
                if (!f.exists()) {
                    f.createNewFile();
                }
                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f), "gbk");
                out.write(sql.toString());
                out.close();
                System.out.println(" sql拼接完成 " + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}