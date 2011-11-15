package AUDRwebJavaBeans;

import java.util.ArrayList;

/*
 * 为了根据不同的5大类得到对应的小的类型
 */
public class Subtypes {

	
	public static String [] getAllEsubtypes(String type)
	{
		ArrayList<String> subtypes=new ArrayList<String>();
		if(type.equals("image"))
		{
			subtypes.add("animal");
			subtypes.add("plant");
			subtypes.add("instrument");
			subtypes.add("scene");
			subtypes.add("acitity");
			subtypes.add("structure");
			subtypes.add("food");
			subtypes.add("medicene");
			
		}
		
		return subtypes.toArray(new String[subtypes.size()]);
		//其他的在下面加
	}
	//得到全部某大类对应的小类
	public static String [] getAllCsubtypes(String type)
	{
		ArrayList<String> subtypes=new ArrayList<String>();
		if(type.equals("图像"))
		{
			subtypes.add("动物");
			subtypes.add("植物");
			subtypes.add("仪器");
			subtypes.add("风景");
			subtypes.add("活动");
			subtypes.add("建筑");
			subtypes.add("食物");
			subtypes.add("医学");
			
		}
		
		return subtypes.toArray(new String[subtypes.size()]);
		
	}
	
	public static String EToC(String str)
	{
		String ss="";
		if(str.equals("image"))
		{
			ss="图像";
		}
		else if(str.equals("animal"))
		{
			ss="动物";
		}
		else if(str.equals("plant"))
		{
			ss="植物";
		}
		else if(str.equals("instrument"))
		{
			ss="仪器";
		}
		else if(str.equals("scene"))
		{
			ss="风景";
		}
		else if(str.equals("food"))
		{
			ss="食物";
		}
		else if(str.equals("activity"))
		{
		    ss="活动";	
		}
		else if(str.equals("medicine"))
		{
			ss="医学";
		}
		else if(str.equals("structure"))
		{
			ss="建筑";
		}
			
		return ss;
	}
}
