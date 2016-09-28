package hq.lib.date;

import java.util.Calendar;

/**
 * The time util
 * @author Danny.Huo
 * @version 1.0
 *
 */
public class DateUtil {
	public static final int YEAR = 0;//year
	public static final int MONTH = 1;//month
	public static final int DATE = 2;//date
	public static final int HOUR = 3;//hour
	public static final int MINUTE = 4;//minute
	public static final int SECOND = 5;//second
	public static final int MILLISECOND = 6;//millisecond

	public static final int FORMAT_YMD = 7;//yyyyMMdd
	public static final int FORMAT_HM = 8;//HH:mm
	public static final int FORMAT_HMS = 9;//HH:mm:ss
	public static final int FORMAT_YMD_HM = 10;//yyyyMMdd HH:mm
	public static final int FORMAT_YMD_HMS = 11;//yyyyMMdd HH:mm:ss
	public static final int FORMAT_YMD_HMS_MS = 12;//yyyyMMdd HH:mm:ss ms

	public static String get(int field){
		Calendar calendar = Calendar.getInstance();
		return get(field,calendar);
	}
	
	public static String get(int field,Calendar calendar) {
		switch (field) {
		case YEAR:
			return calendar.get(Calendar.YEAR) + "";
		case MONTH:
			return calendar.get(Calendar.MONTH) + 1 + "";
		case DATE:
			return calendar.get(Calendar.DATE) + "";
		case HOUR:
			return calendar.get(Calendar.HOUR_OF_DAY) + "";
		case MINUTE:
			return calendar.get(Calendar.MINUTE) + "";
		case SECOND:
			return calendar.get(Calendar.SECOND) + "";
		case MILLISECOND:
			return calendar.get(Calendar.MILLISECOND) + "";
		case FORMAT_YMD:
			return "" + calendar.get(Calendar.YEAR) + "-"
					+ fixLen(calendar.get(Calendar.MONTH) + 1) + "-"
					+ fixLen(calendar.get(Calendar.DATE));
		case FORMAT_HMS:
			return "" + fixLen(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
			+ fixLen(calendar.get(Calendar.MINUTE)) + ":"
			+ fixLen(calendar.get(Calendar.SECOND));
		case FORMAT_YMD_HMS:
			return "" + calendar.get(Calendar.YEAR) + "-"
			+ fixLen(calendar.get(Calendar.MONTH) + 1) + "-"
			+ fixLen(calendar.get(Calendar.DATE))+ " "
			+ fixLen(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
			+ fixLen(calendar.get(Calendar.MINUTE)) + ":"
			+ fixLen(calendar.get(Calendar.SECOND));
		case FORMAT_YMD_HMS_MS:
			return "" + calendar.get(Calendar.YEAR) + "-"
			+ fixLen(calendar.get(Calendar.MONTH) + 1) + "-"
			+ fixLen(calendar.get(Calendar.DATE))+ " "
			+ fixLen(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
			+ fixLen(calendar.get(Calendar.MINUTE)) + ":"
			+ fixLen(calendar.get(Calendar.SECOND)) + " "
			+ calendar.get(Calendar.MILLISECOND);
		case FORMAT_HM:
			return "" + fixLen(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
					+ fixLen(calendar.get(Calendar.MINUTE));
		case FORMAT_YMD_HM:
			return "" + calendar.get(Calendar.YEAR) + "-"
			+ fixLen(calendar.get(Calendar.MONTH) + 1) + "-"
			+ fixLen(calendar.get(Calendar.DATE))+ " "
			+ fixLen(calendar.get(Calendar.HOUR_OF_DAY)) + ":"
			+ fixLen(calendar.get(Calendar.MINUTE));
		}
		return "";
	}
	
	/**
	 * Format the number to string
	 * @param n The number
	 * @return {String}
	 */
	private static String fixLen(int n){
		if(n < 10){
			return "0" + n;
		}
		return "" + n;
	}
	
	/**
	 * Get the date string from a millisecond time<br>
	 * The millisecond time is distance from 1970-1-1 08:00 to the point
	 * @param field Get style
	 * @param millis The point milliseconds
	 * @return {String}
	 */
	public static String get(int field,long millis){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return get(field,calendar);
	}
	
	/**
	 * Change a string date time to milliseconds
	 * @param date The format is yyyy-mm-dd
	 * @return {long}
	 */
	public static long dateToMs(String date){
		if(null != date && !"".equals(date)){
			String[] tmp = date.split("-");
			return dateToMs(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]),Integer.parseInt(tmp[2]));
		}
		return 0;
	}
	
	/**
	 * Change a date to milliseconds
	 * @param year The year
	 * @param month The month, [1-12]
	 * @param day The day
	 * @return {long}
	 */
	public static long dateToMs(int year,int month,int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * Change a time to milliseconds
	 * @param hour The hour
	 * @param minutes The minutes
	 * @return {long}
	 */
	public static long timeToMs(int hour,int minutes){
		return (hour * 60 + minutes) * 60 * 1000;
	}
	
	/**
	 * Change a time to milliseconds
	 * @param time The format is hh:mm
	 * @return {long}
	 */
	public static long timeToMs(String time){
		if(time != null && !"".equals(time)){
			String[] tmp = time.split(":");
			return timeToMs(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
		}
		return 0;
	}
	
	/**
	 * Change a milliseconds time to String time
	 * @param ms The milliseconds
	 * @return {String}
	 *  hh:mm
	 */
	public static String msToTime(long ms){
		if(ms > 0){
			int minutes = (int)(ms / (1000 * 60));
			String h = (int)(minutes / 60)+"";
			String m = minutes % 60+"";
			return (h.length() == 1?"0" + h:h) + ":" +( m.length() == 1?"0" + m:m);
		}else{
			return "00:00";
		}
	}
}
