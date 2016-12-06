# clouder
大数据云计算学习

#sqoop 封装 sqoopExt脚本
  需求:<br/>
  为了方便开发和维护，开发一个可配置的工具命令sqoopExt。<br/>
  具体实现思路:<br/>
    1、Mysql里建一个配置表，配置每个需要抽取的作业信息；<br/>
    2、Java读Mysql配置表生成opt文件，通过Process类执行 ：<br/>
      sqoop --options-file  aa.opt<br/>
    3、执行方式：<br/>
      sqoopExt -date 2015-08-28            -- 抽取全部表<br/>
      sqoopExt -task 1 -date 2015-08-28    --抽取ID为1的表 <br/>
