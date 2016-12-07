package com.lionxxw.clouder.hive.mysql2hive;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String string="select huodong,pv,uv from rpt.rpt_sale_daily limit 20";
		System.out.println(string.split("from")[0].split("select")[1].trim().split(",").length);
	}

}
