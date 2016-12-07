package com.lionxxw.clouder.hive.hive2mysql;

import com.lionxxw.clouder.utils.ParseArgs;
import com.lionxxw.clouder.utils.Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Hive2Mysql {

	/**
	 * @param propertyName
	 */
	public Hive2Mysql(String propertyName) throws Exception {
		init(propertyName);
	}

	Properties prop = new Properties();

	public void init(String propertyName) throws Exception {
		InputStream stream = new FileInputStream(propertyName);
		prop.load(stream);

	}
	
	

	public static void main(String[] args) {
		try {
			if (args.length<1) {
				System.out.println("pls set propertyName!");
				System.exit(1);
			}
//			args = new String[3];
//			args[0]="c:/aa.property";
//			args[1]="-date";
//			args[2]="2015-01-01";
			
			String propertyName = args[0];
			//首个参数以外的其他参数，存到Map里
			ParseArgs parse = new ParseArgs(args);
			
			Hive2Mysql h2m = new Hive2Mysql(propertyName);
			
//			System.out.println(h2m.prop.get("Hive_sql"));
//			System.out.println(h2m.prop.get("Mysql_table"));
			String hive_sql = h2m.prop.get("Hive_sql").toString() ;
			hive_sql = Utils.parse(hive_sql, parse.getMap()) ;
			
			String mysql_table = h2m.prop.get("Mysql_table").toString() ;
			String mysql_columns = h2m.prop.get("mysql_columns").toString() ;
			String mysql_delete = h2m.prop.get("mysql_delete").toString() ;
			mysql_delete = Utils.parse(mysql_delete, parse.getMap()) ;
			
			// insert into mysql_table(pv,uv,huodong) values(123,234,"huodong") ;
			String mysql_sql = "insert into "+mysql_table+" ("+mysql_columns+") values(" ;
			 
			Connection mysqlCon = MyConnection.getMysqlInstance() ;
			Connection myHiveCon = MyConnection.getHiveInstance();
			
			//进行hive查询
			Statement stHive = myHiveCon.createStatement() ;
			ResultSet rsHive = stHive.executeQuery(hive_sql) ;
			
			Statement stMysql = mysqlCon.createStatement() ;
			//删除mysql里此次需要insert的数据
			stMysql.execute(mysql_delete);
			
	        int len = hive_sql.split("from")[0].split("select")[1].trim().split(",").length ;
			System.out.println(len);
	        String value = "";
			while (rsHive.next()) {
				for (int i = 1; i <= len; i++) {
					value += "'"+rsHive.getString(i)+"',";
				}
				//去掉最后一个逗号
				value = value.substring(0, value.length()-1);
				mysql_sql = mysql_sql+value +")";
				System.out.println(value);
				System.out.println(mysql_sql);
				
				stMysql.execute(mysql_sql) ;  //插入到mysql
				//重置value
				value="";
				mysql_sql = "insert into "+mysql_table+" ("+mysql_columns+") values(" ;
				
			}
			
			//关闭连接
			rsHive.close();
			stHive.close();
			stMysql.close();
			mysqlCon.close();
			myHiveCon.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
