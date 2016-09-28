package com.common;

import java.util.Random;

public class Util{
	public static String fontWeight = "font-weight:bold";
	public static String setColor(String str,String color){
		return "<html><font style=\"color:"+color+";"+fontWeight+"\">" + str +"</font></html>";
	}
	
	/**
	 * 随机生成验证码
	 * @return
	 */
	public static String buildValCode(){
		//输出由英语，数字，和中文随机组成的验证文字，具体的组合 方式根据生成随机数确定
		Random random = new Random();
		StringBuilder valCode = new StringBuilder();
		int itmp = 0;
		for(int i = 0; i < 4; i++){
			switch(random.nextInt(3)){
			case 1://生成A到Z的字母
				itmp = random.nextInt(26)+65;
				valCode.append(String.valueOf((char)itmp));
				break;
			case 2://生成数字
				itmp = random.nextInt(9);
				valCode.append(itmp+"");//String.valueOf((char)itmp);
				break;
			default:
				itmp = random.nextInt(10)+48;
				valCode.append(String.valueOf((char)itmp));
				break;
			}
		}
		return valCode.toString();
	}
}
