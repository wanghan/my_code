package toosl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String t  = "201101100612";
		String t0 = "201101110956";
		SimpleDateFormat fmt = new SimpleDateFormat("");
		
		System.out.println(t.substring(6, 8)+"");
		
//		long now = System.currentTimeMillis();
//
//		
//		Date d = fmt.parse(t, new ParsePosition(0));
//		Date d0 = fmt.parse(t0, new ParsePosition(0));
//		System.out.println(d0);
//		System.out.println(d);
//		try {
//			long s = new SimpleDateFormat("yyyyMMddhhmm").parse(t).getTime();
//			long s0 = new SimpleDateFormat("yyyyMMddhhmm").parse(t0).getTime();
//			System.out.println((now-s)/1000/60/60);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//¸ù
	}

}
