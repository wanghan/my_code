package AUDRwebJavaBeans;

import java.util.ArrayList;

/*
 * Ϊ�˸��ݲ�ͬ��5����õ���Ӧ��С������
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
		//�������������
	}
	//�õ�ȫ��ĳ�����Ӧ��С��
	public static String [] getAllCsubtypes(String type)
	{
		ArrayList<String> subtypes=new ArrayList<String>();
		if(type.equals("ͼ��"))
		{
			subtypes.add("����");
			subtypes.add("ֲ��");
			subtypes.add("����");
			subtypes.add("�羰");
			subtypes.add("�");
			subtypes.add("����");
			subtypes.add("ʳ��");
			subtypes.add("ҽѧ");
			
		}
		
		return subtypes.toArray(new String[subtypes.size()]);
		
	}
	
	public static String EToC(String str)
	{
		String ss="";
		if(str.equals("image"))
		{
			ss="ͼ��";
		}
		else if(str.equals("animal"))
		{
			ss="����";
		}
		else if(str.equals("plant"))
		{
			ss="ֲ��";
		}
		else if(str.equals("instrument"))
		{
			ss="����";
		}
		else if(str.equals("scene"))
		{
			ss="�羰";
		}
		else if(str.equals("food"))
		{
			ss="ʳ��";
		}
		else if(str.equals("activity"))
		{
		    ss="�";	
		}
		else if(str.equals("medicine"))
		{
			ss="ҽѧ";
		}
		else if(str.equals("structure"))
		{
			ss="����";
		}
			
		return ss;
	}
}
