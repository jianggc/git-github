package com.web.maven.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 
 * 日期处理类
 * @author madong md_java@163.com
 * @version 2017年1月3日 下午1:01:43
 */
public class DateUtils {
	    private final static  Logger log = LoggerFactory.getLogger(DateUtils.class);
	    public final static String[] weekdays = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	    public final static String yyyy_MM = "yyyy-MM";
	 	public final static String yyyy_MM_dd_HH = "yyyy-MM-dd HH";
	 	public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
		public final static String yyyy_MM_dd = "yyyy-MM-dd";
		public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
		public final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
		public final static String yyyyMMddHHmmsss = "yyyyMMddHHmmsss";
		public final static String yyyyMM = "yyyyMM";
		public final static String yyyyMMdd = "yyyyMMdd";
		private static String defaultDatePattern = null;

		/**
		 * 
		 * 指定时间向前(-n)或向后推n天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:13
		 * @param current java.util.Date
		 * @param days
		 * @return Date
		 */
		public static Date addDay(Date current,int days){
			Calendar calendar=new GregorianCalendar(); 
			calendar.setTime(current);
			calendar.add(Calendar.DATE, days);
			return calendar.getTime();
		}
	    /**
		 * 
		 * 指定时间向前(-n)或向后推n天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:13
		 * @param strdate yyyy-MM-dd
		 * @param days
		 * @return Date
		 */
		public static Date addDay(String strdate,int days){
	
			Date date=null;
			if (ToolUtils.isEmpty(strdate)) {
				return null;
			}
			try {
				date=parseDate(strdate,yyyy_MM_dd);
			} catch (Exception e) {
				return null;
			}
			return addDay(date,days);
		}

		/**
		 * 
		 * 指定时间向前(-n)或向后推n小时
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:23
		 * @param current java.util.Date
		 * @param hour
		 * @return Date
		 */
		public static Date addHour(Date current,int hour){

			Calendar calendar=new GregorianCalendar(); 
			calendar.setTime(current);
			calendar.add(Calendar.HOUR, hour);
			return calendar.getTime();
		}
		
		/**
		 * 
		 * 指定时间向前(-n)或向后推n小时
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:23
		 * @param strdate yyyy-MM-dd HH,yyyy-MM-dd HH:mm:ss
		 * @param hour
		 * @return Date
		 */
		public static Date addHour(String strdate,int hour){
			Date date=null;
			if (ToolUtils.isEmpty(strdate)) {
				return null;
			}
			try {
				if (strdate.indexOf(":")!=-1&&strdate.length()>13) {
					date=parseDate(strdate,yyyy_MM_dd_HH_mm_ss);
				}else{
				    date=parseDate(strdate,yyyy_MM_dd_HH);
				}
			} catch (Exception e) {
				return null;
			}
			return addHour(date,hour);
		}

		/**
		 * 
		 * 指定时间向前(-n)或向后推n分钟
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:35
		 * @param current java.util.Date
		 * @param minute
		 * @return Date
		 */
		public static Date addMinute(Date current,int minute){

			Calendar calendar=new GregorianCalendar(); 
			calendar.setTime(current);
			calendar.add(Calendar.MINUTE, minute);
			return calendar.getTime();
		}
		
		/**
		 * 
		 * 指定时间向前(-n)或向后推n分钟
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:54:35
		 * @param strdate yyyy-MM-dd HH:mm,yyyy-MM-dd HH:mm:ss
		 * @param minute
		 * @return Date
		 */
		public static Date addMinute(String strdate,int minute){
			
			Date date=null;
			if (ToolUtils.isEmpty(strdate)) {
				return null;
			}
			try {
				if (strdate.indexOf(":")!=-1&&strdate.length()>16) {
					date=parseDate(strdate,yyyy_MM_dd_HH_mm_ss);
				}else{
				    date=parseDate(strdate,yyyy_MM_dd_HH_mm);
				}
			} catch (Exception e) {
				return null;
			}
			return addMinute(date,minute);
		}

		/**
		    * 
		    * 指定时间向前(-n)或向后推n个月份
		    * @author madong md_java@163.com
		    * @version 2017年1月3日 下午2:52:18
		    * @param date   java.util.Date
		    * @param nmonth  
		    * @return Date
		    */
			public static Date addMonth(Date date, int nmonth) {
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.MONTH, nmonth);// 增加一个月
					return calendar.getTime();
				} catch (Exception e) {
					return null;
				}
			}
		
		/**
		 * 
		 * 指定时间向前(-n)或向后推n个月份
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:51:41
		 * @param startdate  yyyy-MM-dd  yyyy-MM
		 * @param n_month 
		 * @return Date
		 */
		public static Date addMonth(String strdate, int n_month) {
			Date date=null;
			if (ToolUtils.isEmpty(strdate)) {
				return null;
			}
			try {
			  date=parseDate(strdate,yyyy_MM);
			} catch (Exception e) {
			}
			try {
				date=parseDate(strdate,yyyy_MM_dd);
			} catch (Exception e) {
			}
			if (date==null) {
				return null;
			}
			return addMonth(date,  n_month);
		}
		/**
         * 
         * 指定时间向前(-n)或向后推n秒
         * @author madong md_java@163.com
         * @version 2017年1月3日 下午2:58:50
         * @param current java.util.Date
         * @param second
         * @return Date
         */
		public static Date addSecond(Date current,int second){
			Calendar calendar=new GregorianCalendar(); 
			calendar.setTime(current);
			calendar.add(Calendar.SECOND, second);
			return calendar.getTime();
		}
        /**
		 * 
		 * 指定时间向前(-n)或向后推n秒
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午2:58:50
		 * @param strdate yyyy-MM-dd HH:mm:ss
		 * @param second
		 * @return Date
		 */
		public static Date addSecond(String strdate,int second){
			Date date=null;
			if (ToolUtils.isEmpty(strdate)) {
				return null;
			}
			try {
			  date=parseDate(strdate,yyyy_MM_dd_HH_mm_ss);
			} catch (Exception e) {
				return null;
			}
			return addSecond(date,second);
		}

		/**
		 * 
		 * 2:date1 在date2，即date1小于date2; 
		 * 1:date1在date2后，即date1大于date2; 
		 * 0:date1与date2相等
		 * -1:比较出错
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午3:01:54
		 * @param date1   java.util.Date
		 * @param date2  java.util.Date
		 * @return int
		 */
		public static int compareDate(Date date1, Date date2) {
			try {
				Date dt1 = date1;
				Date dt2 = date2;
				if (dt1.getTime() > dt2.getTime()) {
					return 1;
				} else if (dt1.getTime() < dt2.getTime()) {
					return 2;
				} else {
					return 0;
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				return -1;
			}
		}
		/**
		 * 
		 * 2:date1 在date2，即date1小于date2; 
		 * 1:date1在date2后，即date1大于date2; 
		 * 0:date1与date2相等
		 * -1:比较出错
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午3:01:54
		 * @param date1  yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
		 * @param date2  yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
		 * @return int
		 */
		public static int compareDate(String date1, String date2) {
			String df = yyyy_MM_dd;
			if (date1.length()>10) {
				df=yyyy_MM_dd_HH_mm_ss;
			}
			try {
				Date dt1 = parseDate(date1,df);
				Date dt2 = parseDate(date2,df);
				return compareDate(dt1,dt2);
			} catch (Exception exception) {
				exception.printStackTrace();
				return -1;
			}
		}

		/**
         * 
         * 日期格式转换
         * @author madong md_java@163.com
         * @version 2017年1月3日 下午5:15:08
         * @param strDate
         * @param fromPattern
         * @param toPattern
         * @return String
         */
		public static String convertStringToDate(String strDate,String fromPattern,String toPattern) {
			SimpleDateFormat df = null;
			Date date = null;
			df = new SimpleDateFormat(fromPattern);

			try {
				date = df.parse(strDate);
			} catch (ParseException pe) {
				pe.printStackTrace();
			}

			return format(date,toPattern);
		}

		/**
		 * 
		 * 日期相减(返回天数小于一天返回0)
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午3:59:43
		 * @param date 减数
		 * @param date1 被减数
		 * @return int 返回天数小于一天返回0
		 */
		public static int diffDateReDay(Date date, Date date1) {
			return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
		}

		/**
		 * 
		 * 日期相减(返回天数小于一天返回小数)
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:03:28
		 * @param date 减数
		 * @param date1 被减数
		 * @param scale 小数后保留位数
		 * @return double 返回天数小于一天返回小数
		 */
		public static double diffDateReDayDouble(Date date, Date date1, int scale) {
			return div((getMillis(date) - getMillis(date1)), (24 * 3600 * 1000),
					scale);
		}

		/**
		 * 
		 * 日期相减(返回小时小于一小时返回0)
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:03:51
		 * @param date 减数
		 * @param date1 被减数
		 * @return int  返回小时小于一小时返回0
		 */
		public static int diffDateReHour(Date date, Date date1) {
			return (int) ((getMillis(date) - getMillis(date1)) / (3600 * 1000));
		}

		/**
		 * 
		 * 日期相减(返回小时小于一小时返回小数)
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:04:16
		 * @param date 减数
		 * @param date1 被减数
		 * @param scale 小数后保留位数
		 * @return double 返回小时小于一小时返回小数
		 */
		public static double diffDateReHourDouble(Date date, Date date1, int scale) {
			return div((getMillis(date) - getMillis(date1)), (3600 * 1000), scale);
		}
         /**
		 * 
		 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:04:51
		 * @param v1 被除数
		 * @param v2 除数
		 * @param scale 表示表示需要精确到小数点以后几位。
		 * @return double 两个参数的商
		 */
		public static double div(double v1, double v2, int scale) {
			if (scale < 0) {
				throw new IllegalArgumentException(
						"The scale must be a positive integer or zero");
			}
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}  

		/**
		 * 
		 * 日期格式化
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:05:28
		 * @param date   日期
		 * @param pattern  格式
		 * @return String
		 */
		public static String format(Date date, String pattern) {
			String date2 = "";
			if (ToolUtils.isEmpty(pattern)) {
				pattern = yyyy_MM_dd_HH_mm_ss;
			}

			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				date2 = sdf.format(date);
			}

			return date2;
		}

		/**
		 * 
		 * 获得系统的日期格式
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:07:56
		 * @return String
		 */
		public static String getDatePattern() {
			Locale locale = LocaleContextHolder.getLocale();
			try {
				defaultDatePattern = ResourceBundle.getBundle("yyyy-MM-dd",locale).getString("date.format");
			} catch (MissingResourceException mse) {
				defaultDatePattern = "yyyy-MM-dd";
			}
			return defaultDatePattern;
		}

		/**
		 * 
		 * 获取当前天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:18:08
		 * @return int
		 */
		public static int getDay() {
			Calendar calendar=Calendar.getInstance(); 
			return calendar.get(Calendar.DATE);

		}

		/**
		 * 获取当前是星期几  1星期一 2星期二  3星期三4星期四5星期五6星期六 7星期日
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:19:18
		 * @return int
		 */
		public static int getDayOfWeek() {
			Calendar calendar=Calendar.getInstance(); 
			return calendar.get(Calendar.DAY_OF_WEEK)-1;
			
		}
		/**
		 * 
		 * 获取指定时间星期几  
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:41:37
		 * @param strdate yyyy-MM-dd
		 * @return String
		 */
		public static int getDayOfWeek(String strdate) {
			Calendar calendar=Calendar.getInstance(); 
			try {
				calendar.setTime(parseDate(strdate,yyyy_MM_dd));
				return calendar.get(Calendar.DAY_OF_WEEK)-1;
			} catch (Exception e) {
				return -1;
			}
		}
		/**
		 * 获取当前是星期几   星期一  星期二   星期三  星期四  星期五  星期六  
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:19:18
		 * @return int
		 */
		public static String getDayOfWeekStr() {
			return weekdays[getDayOfWeek()];
		}
		/**
		 * 
		 * 获取指定时间星期几  
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:41:37
		 * @param strdate yyyy-MM-dd
		 * @return String
		 */
		public static String getDayOfWeekStr(String strdate) {
			return weekdays[getDayOfWeek(strdate)];
		}

		/**
         * 
         * 获得下月的第一天
         * @author madong md_java@163.com
         * @version 2017年1月3日 下午5:31:16
         * @param date
         * @return Date
         */
		public static Date getFirstDateOfNextMonth(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int month = calendar.get(Calendar.MONTH);
			calendar.set(Calendar.MONTH, month+1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();

		}

		/**
		 * 获得当月第一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:19:41
		 * @return String
		 */
		public static String getFirstDayOfMonth() {
			String str = "";
			Calendar lastDate = Calendar.getInstance();
			lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
			str = format(lastDate.getTime(),yyyy_MM_dd);
			return str;
		}

		/**
         * 
         * 获得指定日期月份的第一天
         * @author madong md_java@163.com
         * @version 2017年1月3日 下午5:30:23
         * @param date
         * @return Date
         */
		public static Date getFirstDayOfMonth(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return calendar.getTime();
		}

		/**
		 * 
		 * 根据指定日期过的指定对应日期月份的第一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:20:02
		 * @param startdate
		 * @return String
		 */
		public static String getFirstDayOfMonthStr(Date date) {
			try {
				String str = "";
				Calendar cd = Calendar.getInstance();
				cd.setTime(date);
				cd.set(Calendar.DATE, 1);// 设为当前月的1号
				str = format(cd.getTime(),yyyy_MM_dd);;
				return str;
			} catch (Exception e) {
				return null;
			}
		}
		/**
		 * 
		 * 获取当前小时
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:18:52
		 * @return int
		 */
		public static int getHourOfDay() {
			Calendar calendar=Calendar.getInstance(); 
			return calendar.get(Calendar.HOUR_OF_DAY);

		}

		/**
		 * 
		 * 获得当月最后一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:21:53
		 * @return String
		 */
		public static String getLastlyDayOfMonth() {
			String str = "";
			Calendar lastDate = Calendar.getInstance();
			lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
			lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
			lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
			str = format(lastDate.getTime(),yyyy_MM_dd);
			return str;
		}

		/**
         * 
         * 获得指定日期月份的最后一天
         * @author madong md_java@163.com
         * @version 2017年1月3日 下午5:30:55
         * @param date
         * @return Date
         */
		public static Date getLastlyDayOfMonth(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int month = calendar.get(Calendar.MONTH);
			calendar.set(Calendar.MONTH, month+1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			return calendar.getTime();
		}

		/**
		 * 
		 * 获得指定日期 对应月份的最后一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:21:53
		 * @return String
		 */
		public static String getLastlyDayOfMonthStr(Date date) {
			String str = "";
			Calendar lastDate = Calendar.getInstance();
			lastDate.setTime(date);
			lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
			lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
			lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
			str = format(lastDate.getTime(),yyyy_MM_dd);
			return str;
		}

		/**
		 * 
		 * 返回毫秒
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:49:18
		 * @param date 日期
		 * @return long  
		 */ 
		private static long getMillis(Date date) {
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(date);
			return c.getTimeInMillis();
		}

		/**
		 * 
		 * 获取当前月
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:19:08
		 * @return int
		 */
		public static int getMonth() {
			Calendar calendar=Calendar.getInstance(); 
			return calendar.get(Calendar.MONTH)+1;

		}

		/**
		 * 
		 * 获得前一天时间 格式为yyyy-MM-dd
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:54:17
		 * @return String
		 */
		public static String getPreviousDay() {
			Calendar cld = Calendar.getInstance();
			cld.add(Calendar.DATE, - 1);
			Date monthstart = cld.getTime();
			return format(monthstart, yyyy_MM_dd);
		}


		/**
		 * 
		 * 获得前一天时间 格式为pattern
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:00:00
		 * @param pattern
		 * @return String
		 */
		public static String getPreviousDay(String pattern) {
			Calendar cld = Calendar.getInstance();
			cld.set(Calendar.DATE, cld.get(Calendar.DATE) - 1);
			Date monthstart = cld.getTime();
			return format(monthstart, pattern);
		}

		/**
		 * 
		 * 获得上月最后一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:00:15
		 * @return String
		 */
		public static String getPreviousMonthEndDay() {
			String str = "";
			Calendar lastDate = Calendar.getInstance();
			lastDate.add(Calendar.MONTH, -1);// 减一个月
			lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
			lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
			str = format(lastDate.getTime(),yyyy_MM_dd);
			return str;
		}

		/**
		 * 
		 * 获得上月第一天
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:01:13
		 * @return String
		 */
		public static String getPreviousMonthFirstDay() {
			String str = "";
			Calendar lastDate = Calendar.getInstance();
			lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
			lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
			str = format(lastDate.getTime(),yyyy_MM_dd);
			return str;
		}
		/**
		 * 
		 * 获得当前时间
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:07:24
		 * @return Date
		 */
		public  static Date getSystemTime() {
			Calendar now = Calendar.getInstance();
			return now.getTime();
		}

		/**
		 * 
		 * 获得系统时间   格式根据系统默认格式
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:08:49
		 * @return String
		 */
		public static String getSystemTimeStr() {
			Date aDate = new Date();
			
			SimpleDateFormat df = null;
			String returnValue = "";
			
			if (aDate != null) {
				df = new SimpleDateFormat(getDatePattern());
				returnValue = df.format(aDate);
			}
			return (returnValue);
		}
        /**
		 * 
		 * 获得系统时间   格式根据pattern
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:08:49
		 * @return String
		 */
		public static String getSystemTimeStr(String pattern) {
			Date aDate = new Date();

			SimpleDateFormat df = null;
			String returnValue = "";

			if (aDate != null) {
				df = new SimpleDateFormat(pattern);
				returnValue = df.format(aDate);
			}
			return (returnValue);
		}

        /**
		 * 
		 * 获取当前年
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午4:19:29
		 * @return int
		 */
		public static int getYear() {
			Calendar calendar=Calendar.getInstance(); 
			return calendar.get(Calendar.YEAR);
		}

        /**
          * 
          * 判断是平年还是闰年
          * @author madong md_java@163.com
          * @version 2017年1月3日 下午4:14:37
          * @param year
          * @return boolean
          */
		 public static boolean isLeapyear(int year) {  
	        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {  
	            return true;  
	        } else {  
	            return false;  
	        }  
	    }

        /**
		 * 
		 * 日期的字符串格式转Date对象实例
		 * @author madong md_java@163.com
		 * @version 2017年1月3日 下午5:03:11
		 * @param strdate   日期
		 * @param pattern  日期格式
		 * @return Date
		 */
		public static Date parseDate(String strdate, String pattern) {
			Date date=null;
			if (ToolUtils.isEmpty(pattern)) {
				pattern = yyyy_MM_dd_HH_mm_ss;
			}
			try {
				if (ToolUtils.isNotEmpty(strdate)) {
					SimpleDateFormat sdf = new SimpleDateFormat(pattern);
					date = sdf.parse(strdate);
				}
			} catch (ParseException e) {
				log.error("parseDate(String date, String pattern) ", e);
				e.printStackTrace();
			}
			return date;
		}

}
