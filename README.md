# clouder
大数据云计算学习

#sqoop 封装 sqoopExt脚本
    需求:
        为了方便开发和维护，开发一个可配置的工具命令sqoopExt
    具体实现思路:
        1、Mysql里建一个配置表，配置每个需要抽取的作业信息;
        2、Java读Mysql配置表生成opt文件，通过Process类执行
          sqoop --options-file  aa.opt<br/>
        3、执行方式：<br/>
          sqoopExt -date 2015-08-28            -- 抽取全部表
          sqoopExt -task 1 -date 2015-08-28    --抽取ID为1的表

#common 工程封装一些工具类
    DBUtil jdbc数据库连接工具类
    FileUtils 文件操作工具类
    ParseArgs java main方法参数map封装
    Utils   根据入参替换变量
    
#hive 工程
    ##udf 用户自定义函数封装
        GetActID
            用法: GetActID(url)
            效果: 截取符合指定正则表达式的字符
    ##hivef
        将原先的hive -f 命令,改为可传参的 hiveF 函数
        shell脚本:
            #!/bin/sh
            . /etc/profile
            sql=`java -jar /home/hadoop/jar/hiveF.jar $*`
            echo $sql
            
            hive -e "$sql" -i /home/hadoop/bin/hive-init.sql
    ##hive2mysql
        封装 hive2mysql 脚本命令
        shell脚本:
            #!/bin/sh
            . /etc/profile
            java -jar /home/hadoop/jar/hive2mysql.jar $*
        实现功能:
            第一个参数:为properties文件路径
                格式:
                    mysql_delete=delete from test.rpt_daily_act_visit where date='{$date}'
                    Hive_sql=select date,actid,pv,uv from rpt_daily_act_visit where date='{$date}'
                    Mysql_table=test.rpt_daily_act_visit
                    mysql_columns=date,actid,pv,uv
            后面参数
                -date 2015-08-28 格式
            将hive表的数据导入mysql中