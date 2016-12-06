package com.lionxxw.clouder.process;

import com.lionxxw.clouder.bean.SqoopParams;
import com.lionxxw.clouder.sqoop.GenerateOptFile;
import com.lionxxw.clouder.utils.DBUtil;
import com.lionxxw.clouder.utils.ParseArgs;
import com.lionxxw.clouder.utils.Utils;

import java.util.List;

/**
 * sqoop脚本执行
 * Package com.lionxxw.clouder.proccess
 * Project clouder
 * Company www.baofoo.com
 * Author wangjian@baofoo.com
 * Created on 2016/12/6 17:10
 * version 1.0.0
 */
public class SqoopProcess {

    /**
     * 支持参数 -task   查询指定taskId
     *         -date   根据日期
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //首个参数以外的其他参数，存到Map里
        ParseArgs parse = new ParseArgs(args);
        List<SqoopParams> sqoopParamses = DBUtil.queryTaskJobByParam(parse);
        if (null != sqoopParamses && sqoopParamses.size() > 0){
            for (SqoopParams params : sqoopParamses){
                if (null != params.getTarget_where()){
                    // 把sql字符串进行处理，把里面出现map的key的地方用value替换
                    params.setTarget_where(Utils.parse(params.getTarget_where(), parse.getMap()));
                }
                if (null != params.getHive_partition_value()){
                    params.setHive_partition_value(Utils.parse(params.getHive_partition_value(), parse.getMap()));
                }
                String path = GenerateOptFile.generate(params);
                try{
                    Runtime rt = Runtime.getRuntime();
                    Process proc = rt.exec("sqoop --options-file "+path);
                    int exitVal = proc.waitFor();
                    System.out.println("Process exitValue: " + exitVal);
                } catch (Throwable t){
                    t.printStackTrace();
                }
            }
        }
    }
}
