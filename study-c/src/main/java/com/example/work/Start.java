package com.example.work;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.work.SqlData.getSqlData;

@Slf4j
public class Start {

    public static void main(String[] args) {
        try {
            HashMap<String, ArrayList<String>> map = getSqlData();
            for (Map.Entry<String, ArrayList<String>> m : map.entrySet()) {
                ExecuteSql sql = new ExecuteSql(m.getKey(), m.getValue());
                Thread dataThread = new Thread(sql);
                dataThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
