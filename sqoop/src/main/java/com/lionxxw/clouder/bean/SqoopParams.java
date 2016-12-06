package com.lionxxw.clouder.bean;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * sqoop导入文件生成参数
 * Package com.lionxxw.clouder.bean
 * Project clouder
 * Company www.baofoo.com
 * Author wangjian@baofoo.com
 * Created on 2016/12/6 14:58
 * version 1.0.0
 */
@Getter
@Setter
public class SqoopParams {
    private Integer id;                             // 主键id
    private String task_name;                       // 作业名称
    private String connect;                         // mysql连接路径
    private String username;                        // 用户名
    private String pwd;                             // 密码
    private String target_table;                    // 源数据表名(读取的表)
    private String target_columns;                  // 查询字段(字段之间用","分隔)
    private String target_where;                    // 源数据表查询条件
    private String target_dir;                      // 目标hdfs临时目录
    private String split_by;                        // 切分字段(前提m大于1时,必须设置)
    private Integer m;                              // map数定义
    private String fileds_terminated_by;            // 字段分隔符
    private Boolean  hive_import = false;           // 是否导入至hive,默认到hdfs
    private String   hive_table;                    // hive表
    private Boolean  hive_overwrite = true;         // 是否覆盖原表(默认true)
    private String hive_partition_key;              // 分区字段key
    private String hive_partition_value;            // 分区字段value

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}