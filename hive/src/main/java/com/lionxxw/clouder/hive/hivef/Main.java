package com.lionxxw.clouder.hive.hivef;

import com.lionxxw.clouder.utils.ParseArgs;
import com.lionxxw.clouder.utils.Utils;

import java.io.File;

public class Main {

	/**
	 * @param    ../*.sql  -date "2013-01-01"  -date1 2013-01-01
	 */
	public static void main(String[] args) throws Exception{
//		// TODO Auto-generated method stub
//		args = new String[3];
//		args[0]="e:/test.sql";
//		args[1]="-date";
//		args[2]="2013-01-01";
//		//args[3]="-label";
//		//args[4]="2013-01-02";
		
		//首个参数以外的其他参数，存到Map里
		ParseArgs parse = new ParseArgs(args);
		// 把sql文件转成一个sql字符串
		String sql = Utils.getSql(new File(args[0])) ;
		// 把sql字符串进行处理，把里面出现map的key的地方用value替换
		String str=Utils.parse(sql,parse.getMap());
		System.out.println(str);
		
	}

}
