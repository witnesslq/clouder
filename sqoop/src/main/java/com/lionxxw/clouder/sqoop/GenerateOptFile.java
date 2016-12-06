package com.lionxxw.clouder.sqoop;

import com.lionxxw.clouder.bean.SqoopParams;
import com.lionxxw.clouder.utils.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 生成opt文件
 * Package com.lionxxw.clouder.sqoop
 * Project clouder
 * Company www.baofoo.com
 * Author wangjian@baofoo.com
 * Created on 2016/12/6 14:56
 * version 1.0.0
 */
public class GenerateOptFile {
    private final static String LOCATIONPATH = "/sqoop/opts/";
    private final static String SUFFIX = ".opt";

    public static String generate(SqoopParams params) throws IOException {
        if (null == params){
            throw new RuntimeException("this params is null");
        }
        if (null == params.getTask_name() || "".equals(params.getTask_name())){
            throw new RuntimeException("this task_name of params is null");
        }
        String filePath = LOCATIONPATH+params.getTask_name()+SUFFIX;
        FileUtils.createFile(filePath);
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
        writer.write("import");
        writer.write("\r\n");
        if (null != params.getConnect()){
            writer.write("--connect");
            writer.write("\r\n");
            writer.write("\""+params.getConnect()+"\"");
            writer.write("\r\n");
        }
        if (null != params.getUsername()) {
            writer.write("--username");
            writer.write("\r\n");
            writer.write(params.getUsername());
            writer.write("\r\n");
        }
        if (null != params.getPwd()){
            writer.write("--password");
            writer.write("\r\n");
            writer.write(params.getPwd());
            writer.write("\r\n");
        }
        if (null != params.getTarget_table()) {
            writer.write("--table");
            writer.write("\r\n");
            writer.write(params.getTarget_table());
            writer.write("\r\n");
        }
        if (null != params.getTarget_columns()) {
            writer.write("--columns");
            writer.write("\r\n");
            writer.write("\""+params.getTarget_columns()+"\"");
            writer.write("\r\n");
        }
        if (null != params.getTarget_where()) {
            writer.write("--where");
            writer.write("\r\n");
            writer.write("\"" + params.getTarget_where() + "\"");
            writer.write("\r\n");
        }
        if (null != params.getM()) {
            writer.write("-m");
            writer.write("\r\n");
            writer.write(String.valueOf(params.getM()));
            writer.write("\r\n");
            if (params.getM() > 1 && null != params.getSplit_by()){
                writer.write("--split-by");
                writer.write("\r\n");
                writer.write(params.getSplit_by());
                writer.write("\r\n");
            }
        }
        if (null != params.getTarget_dir()) {
            writer.write("--target-dir");
            writer.write("\r\n");
            writer.write(params.getTarget_dir());
            writer.write("\r\n");
        }
        if (null != params.getHive_import() && params.getHive_import()){
            writer.write("--hive-import");
            writer.write("\r\n");
            if (null != params.getHive_table()) {
                writer.write("--hive-table");
                writer.write("\r\n");
                writer.write(params.getHive_table());
                writer.write("\r\n");
            }
            if (null == params.getHive_import() || params.getHive_overwrite()){
                writer.write("--hive-overwrite");
                writer.write("\r\n");
            }
            if (null != params.getHive_table()) {
                writer.write("--hive-partition-key");
                writer.write("\r\n");
                writer.write(params.getHive_partition_key());
                writer.write("\r\n");
            }
            if (null != params.getHive_table()) {
                writer.write("--hive-partition-value");
                writer.write("\r\n");
                writer.write(params.getHive_partition_value());
                writer.write("\r\n");
            }
        }

        writer.flush();
        writer.close();

        return  filePath;
    }

    public static void main(String[] args) {
        SqoopParams params = new SqoopParams();
        params.setTask_name("task1");
        params.setConnect("jdbc:mysql://hadoop-master.lionxxw.com:3306/test");
        try {
            generate(params);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
