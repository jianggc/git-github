package com.web.maven.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * 
 * @ClassName ToolUtils
 * @Description 常用工具方法通用类
 * @author madong md_java@163.com
 * @date 2016年12月30日 下午1:08:25
 */
public class ToolUtils {
	private final static  Logger log = LoggerFactory.getLogger(ToolUtils.class);
	private final static  String  EMPTY = "";
	private final static  String  IS_WORD = "^\\w+$";
	private final static  String  IS_LETTER = "[a-zA-Z]+";
	private final static  String  IS_NUMBER = "[0-9]+";
	private final static  String  IS_EMAIL = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
	private final static  String  IS_PHONEN_UMBER = "(\\(\\d{3}\\)|\\d{3,4}-)?\\d{7,8}$";
	private final static  String  IS_MOBILE_NUMBER = "0?(13|15|18)\\d{9}";
	private final static  String  IS_ZIP_CODE = "\\d{6}";
	private final static  String  IS_CHARACTER = "[\\u4e00-\\u9fa5]";
	private final static  String  IS_DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";;

	
	public static String arrayToStr(Serializable[] array, String insertString)
	  {
	    String strs = "";
	    if (array != null) {
	      for (int i = 0; i < array.length; ++i) {
	        if (i < array.length - 1)
	          strs = strs + array[i] + insertString;
	        else {
	          strs = strs + array[i];
	        }
	      }
	    }
	    return strs;
	  }
	/**
	 * 
	 * 判断list集合是否不为空
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午12:56:12
	 * @param inputString
	 * @return boolean
	 */
	public static boolean isNotEmpty(List<?> list) {
		if (list == null||list.isEmpty()) {
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * 判断list集合是否为空
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午12:56:12
	 * @param inputString
	 * @return boolean
	 */
	public static boolean isEmpty(List<?> list) {
		if (list == null||list.isEmpty()) {
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 
	 * 判断字符串中是否为NULL、空
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午12:56:12
	 * @param inputString
	 * @return boolean
	 */
	public static boolean isEmpty(String inputString) {
		if (inputString == null||inputString.trim().length()==0) {
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * 判断字符串中是否不为NULL、空
	 * @author madong md_java@163.com
	 * @version 2017年1月3日 下午12:56:55
	 * @param inputString
	 * @return boolean
	 */
	public static boolean isNotEmpty(String inputString) {
		if (inputString == null||inputString.trim().length()==0) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * 判断字符串中是否仅包含字母，数字，下划线
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:44:46
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isWord(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		boolean isWord=inputString.matches(IS_WORD);
		if (isWord) {
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * 判断该字符是否为字母
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:44:36
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isLetter(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		
		boolean isLetter=inputString.matches(IS_LETTER);
		if (isLetter) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 判断字符串是否为数字
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:45:16
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isNumber(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		
		boolean isNumber=inputString.matches(IS_NUMBER);
		if (isNumber) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 检测email地址是否合法
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:45:25
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isEmail(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		Pattern pattern = Pattern.compile(IS_EMAIL,Pattern.CASE_INSENSITIVE);
		boolean isEmail=pattern.matcher(inputString).matches();
		if (isEmail) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 检测是否为合法的电话号码
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:45:35
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isPhoneNumber(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		boolean isPhoneNumber=Pattern.matches(IS_PHONEN_UMBER, inputString);
		if (isPhoneNumber) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 检测是否为合法的手机号码
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:45:44
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isMobileNumber(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		boolean isMobileNumber=Pattern.matches(IS_MOBILE_NUMBER, inputString);
		if (isMobileNumber) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 检测是否为合法的邮编
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:45:51
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isZipCode(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		boolean isZipCodeNumber=Pattern.matches(IS_ZIP_CODE, inputString);
		if (isZipCodeNumber) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * 判断字符是不是汉字
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:00
	 * @param inputString
	 * @return boolean
	 * @throws
	 */
	public static boolean isCharacter(String inputString) {
		if (isEmpty(inputString)) {
			return false;
		}
		boolean isCharacter=inputString.matches(IS_CHARACTER);
		if (isCharacter) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * 验证日期 例子：
	 * (<ol><li>20101211 11:11:11</li>
		<li>2010-12-11 11:11:11</li>
		<li>2010/12/11 11:11:11</li>
		<li>20101211</li>
		<li>2010/12/11</li>
		<li>2010-12-11</li></ol>
		)
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:09
	 * @param str
	 * @return boolean
	 * @throws
	 */
	public static boolean isDate(String inputString) {
//		String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";   
//		 String eL= "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";   
		if (isEmpty(inputString)) {
			return false;
		}
		 Pattern pattern = Pattern.compile(IS_DATE);
		 boolean isDate= pattern.matcher(inputString).matches();
		if (isDate) {
			return true;
		}else{
			return false;
		}
	}



	/**
	 * 
	 * 获得长度为n的随机数
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:29
	 * @param n
	 * @return String
	 * @throws
	 */
	public static String random(int n) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < n; i++) {
			int a = (int) (Math.random() * 10);
			str.append(a);
		}
		return str.toString();
	}

	/**
	 * 
	 * 获得长度为n的随机数
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:41
	 * @param n
	 * @return String
	 * @throws
	 */
	public static String randomChar(int n) {

		StringBuffer str = new StringBuffer();

		char[] cha = new char[52];
		int i = 0;
		for (; i < 26; i++) {
			cha[i] = (char) (65 + i);
		}
		for (; i < 52; i++) {
			cha[i] = (char) (71 + i);
		}
		// cha数组中，此时元素为A_Za-z
		Random random = new Random();
		for (i = 0; i < n; i++) {
			int rand = random.nextInt(52);
			str.append(cha[rand]);
		}
		return str.toString();
	}

	/**
	 * 
	 * 获得取随字母加数字 长度为n
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:48
	 * @param n
	 * @return String
	 * @throws
	 */
	public static String randomString(int n) {
		StringBuffer str = new StringBuffer();
		char[] cha = new char[62];
		int i = 0;
		for (; i < 26; i++) {
			cha[i] = (char) (65 + i);
		}
		for (; i < 52; i++) {
			cha[i] = (char) (71 + i);
		}
		for (; i < 62; i++) {
			cha[i] = (char) (-4 + i);
		}
		// cha数组中，此时元素为A_Za-z0-9
		Random random = new Random();
		for (i = 0; i < n; i++) {
			int rand = random.nextInt(62);
			str.append(cha[rand]);
		}
		return str.toString();
	}


	/**
	 * 
	 * 将String转换成unicode编码格式
	 * @author madong md_java@163.com
	 * @version 2016年12月30日 下午6:46:58
	 * @param str
	 * @return String
	 * @throws
	 */
	public static String unicodeEncoding(String str) {
		if (str == null||str.trim().length()==0) {
			return EMPTY;
		}
		StringBuffer unicodeBytes = new StringBuffer();
		for (int byteIndex = 0; byteIndex < str.length(); byteIndex++) {
			String hexB = Integer.toHexString(str.charAt(byteIndex));
			unicodeBytes.append("\\u");
			if (hexB.length() <= 2) {
				unicodeBytes.append("00");
			}
			unicodeBytes.append(hexB);
		}
		return unicodeBytes.toString();
	}

}
