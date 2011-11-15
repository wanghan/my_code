package AUDRwebServlets;

import java.util.ArrayList;
import java.util.List;

import AUDRwebJavaBeans.TextModel;

public class Test {

	public static List getList()
	{
		
	 		List list = new ArrayList();
		 
			TextModel t1 = new TextModel();
			t1.setUrl("www.baidu.com");
			t1.setTitle("百度");
			t1.setAuthor("李彦宏");
			t1.setCreateTime("2010.02.03");
			t1.setEditTime("2010.04.23");
			t1.setTextType("html");
			t1.setContent("百度搜索引擎");
			list.add( t1 );
			
			TextModel t2 = new TextModel();
			t2.setUrl("www.google.com");
			t2.setTitle("google");
			t2.setTextType("html");
			t2.setContent("google搜索引擎");
			list.add( t2 );
			
			
			TextModel t3 = new TextModel();
			t3.setUrl("www.sina.com.cn");
			t3.setTitle("新浪");
			t3.setTextType("html");
			t3.setContent("新浪新浪新浪新浪新浪新浪新浪新浪新浪");
			list.add( t3 );
			
			TextModel t4 = new TextModel();
			t4.setUrl("www.163.com");
			t4.setTitle("网易");
			t4.setTextType("html");
			t4.setContent("网易网易网易网易网易网易网易网易网易网易网易网易网易网易");
			list.add( t4 );
			
			TextModel t5 = new TextModel();
			t5.setUrl("www.1g1g.com");
			t5.setTitle("亦歌");
			t5.setTextType("html");
			t5.setContent("亦歌亦歌亦歌亦歌亦歌亦歌亦歌亦歌");
			list.add( t5 );
			
			
			TextModel t6 = new TextModel();
			t6.setUrl("www.javaeye.com");
			t6.setTitle("javaeye");
			t6.setTextType("html");
			t6.setContent("javaeye javaeye javaeyejavaeye  javaeye");
			list.add( t6 );
			
			return list;
	}
}
