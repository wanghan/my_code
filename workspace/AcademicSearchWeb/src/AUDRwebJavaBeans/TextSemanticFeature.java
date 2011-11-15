package AUDRwebJavaBeans;

import java.util.Hashtable;

/*
 * �ı���������Ϣ
 */
public class TextSemanticFeature {
	
	public static int ATTRIBUTENUMBERS = 17;
	
	private String []textSFItems={"sfid","TextType","Title","Subject","Url",
			"Author","Keyword","Remark","WordCount","Template","Department","Sort",
			"LastAuthor","ModifyCount","EditTime","CreatTime","PrintTime",};
	/*
	 * key:������
	 */
	private Hashtable<String,String> SFitemsValue=new Hashtable<String,String>();//���е�������Ϊһ��HASHTANLE
	public TextSemanticFeature()
	{
	}
	
	public TextSemanticFeature(	String sfid,	String texttype,String title,String subject ,String url,
			String au,String keyword,String remark,String WordCount,String Template,String Department,String Sort,
			String LastAuthor,String ModifyCount,String EditTime,String CreatTime,String PrintTime)
	{
		 SFitemsValue.put(textSFItems[0], sfid);
		 SFitemsValue.put(textSFItems[1], texttype);
		 SFitemsValue.put(textSFItems[2], title);
		 SFitemsValue.put(textSFItems[3],subject);
		 SFitemsValue.put(textSFItems[4], url);
		 SFitemsValue.put(textSFItems[5], au);
		 SFitemsValue.put(textSFItems[6], keyword);
		 SFitemsValue.put(textSFItems[7], remark);
		 SFitemsValue.put(textSFItems[8], WordCount);
		 SFitemsValue.put(textSFItems[9], Template);
		 SFitemsValue.put(textSFItems[10], Department);
		 SFitemsValue.put(textSFItems[11], Sort);
		 SFitemsValue.put(textSFItems[12], LastAuthor);
		 SFitemsValue.put(textSFItems[13],ModifyCount);
		 SFitemsValue.put(textSFItems[14], EditTime);
		 SFitemsValue.put(textSFItems[15],CreatTime);
		 SFitemsValue.put(textSFItems[16], PrintTime);
	}
	
	
	//SFID���õ�
	public String  getSfid()
	{
		String sfid=get(textSFItems[0]);
		return sfid;
	}
	//�ܵķ���
	public void set(String key,String value)
	{
		 SFitemsValue.put(key, value);
	}
	
	public String get(String key)
	{
		 return SFitemsValue.get(key);
	}
	
	//�õ����е��������ֵ
	public Hashtable<String,String> getAllItemsValue()
	{
		return SFitemsValue;
	}
	//�õ����е�������
	public String[] getAllItems()
	{
		return textSFItems;
	}
	public String getSingleItem(int i)
	{
		if(i>0&&i<textSFItems.length)
		  return textSFItems[i];
		else 
			return null;
	}
	
	
}
