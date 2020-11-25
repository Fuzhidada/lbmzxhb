package com.example.operation;

import java.util.ArrayList;

public class SS {
    static ArrayList<String> ll=new ArrayList<>();

    public void add(){
        ll.add("23");
    }

    public void delete(){
        ll.clear();
    }

    public String  get(){
       return ll.get(0);
    }


}
