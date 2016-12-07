package com.lionxxw.clouder.hive.udf;


import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * hive用户自定义函数
 * 获取专题name
 * 用法：GetActID(URL)
 */
public class GetActID extends UDF{
	
	public String evaluate(final String url) {
		if(url == null || url.trim().length()==0)
		{
			return null;
		}
		// sale/baJXdddObUm
		Pattern p = Pattern.compile("sale/([a-zA-Z0-9]+)");
		
        Matcher m = p.matcher(url);
        if (m.find()) {
        	return m.group(0).split("\\/")[1];
        }
		return null;
	}
	
	public int evaluate(int type) {
		
		return 0;
	}
	
	
	public static void main(String[] argc){
		GetActID gh=new GetActID(); 
		System.out.println(gh.evaluate("http://cms.yhd.com/sale/baJXdddObUm?tc=ad.0.0.15070-41524284.1&tp=1.1.36.2.1.LZ2FjTk-10-5JrC`&ti=3PAMEe"));
	}
	
}
