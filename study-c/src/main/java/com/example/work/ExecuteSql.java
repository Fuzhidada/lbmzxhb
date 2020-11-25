package com.example.work;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.example.work.OdbcConnection.close;
import static com.example.work.OdbcConnection.getConnection;

@Slf4j
public class ExecuteSql implements Runnable {

    private String sql;
    private ArrayList<String> title;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private ArrayList<String> columnList;
    private static final int MAX_LENGTH = 5000;

    ExecuteSql(String sql, ArrayList<String> title) {
        this.sql = sql;
        this.title = title;
    }

    @Override
    public void run() {
        if (executeSql(sql)) {
            WriteFile writeFile = new WriteFile(title);
            ArrayList<String[]> resultList;
            while (!CollectionUtils.isEmpty(resultList = getData())) {
                writeFile.writeContent(resultList);
            }
            close(con, ps, rs);
            writeFile.close();
            log.info("{} 写入完成", title.get(0));
        }
    }

    private boolean executeSql(String sql) {
        log.info("开始执行SQL");
        try {
            con = getConnection();
            ps = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = ps.executeQuery();
            columnList = Lists.newArrayList();
            if (rs != null) {
                int column = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= column; i++) {
                    columnList.add(rs.getMetaData().getColumnName(i));
                }
            }
        } catch (Exception e) {
            log.error("{}  {}-------> 执行异常", LocalDateTime.now(), title.get(0));
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private ArrayList<String[]> getData() {
        ArrayList<String[]> resultList = Lists.newArrayList(); //每次新new个对象 回收之前的list 便于gc防止内存溢出
        try {
            int length = columnList.size();
            int count = 0;
            while (count++ < MAX_LENGTH) {
                if (!rs.next()) {
                    break;
                } else {
                    //内存溢出原因?
                    String[] temp = new String[length];
                    for (int i = 0; i < length; i++) {
                        temp[i] = rs.getString(columnList.get(i));
                    }
                    resultList.add(temp);
                }
            }
        } catch (SQLException e) {
            log.error("{}  {}-------> 解析异常", LocalDateTime.now(), title.get(0));
            e.printStackTrace();
//            close(con, ps, rs);
        }
        return resultList;
    }
}
