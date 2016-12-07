package com.lionxxw.clouder.utils;

import com.lionxxw.clouder.bean.SqoopParams;
import com.lionxxw.clouder.sqoop.GenerateOptFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Package com.lionxxw.clouder.utils
 * Project clouder
 * Company www.baofoo.com
 * Author wangjian@baofoo.com
 * Created on 2016/12/7 9:40
 * version 1.0.0
 */
public class MyDBUtil extends DBUtil {

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PWD);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from task_job");
        List list = populate(rs, SqoopParams.class);
        for(int i = 0 ; i<list.size() ; i++){
            SqoopParams params = (SqoopParams) list.get(i);
            System.out.println(params.toString());
            GenerateOptFile.generate(params);
        }
    }

    public static List<SqoopParams> queryTaskJobByParam(ParseArgs args) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PWD);
        Statement st = conn.createStatement();
        ResultSet rs;
        if (args.getMap().containsKey("task")){
            rs = st.executeQuery("select * from task_job where id ="+args.getMap().get("task"));
        }else{
            rs = st.executeQuery("select * from task_job");
        }
        return populate(rs, SqoopParams.class);
    }
}