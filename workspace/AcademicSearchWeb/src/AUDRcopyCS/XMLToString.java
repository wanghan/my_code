/*
 * ��XMLת����һ���ַ���,����Ŀɸ����Բ���,XML�ĸ�ʽ�ı����ҲҪ��Ӧ�ĸı�
 */
package AUDRcopyCS;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import AUDRwebJavaBeans.ImageBFObj;
import AUDRwebJavaBeans.ImageSemanticFeature;
import AUDRwebJavaBeans.TextBasicFeature;
import AUDRwebJavaBeans.TextSemanticFeature;

public class XMLToString 
{
	private ArrayList<ImageBFObj> BFS;
	private ArrayList<ImageSemanticFeature> SFS;
	private static int pageNum=100;//ÿһҳ��ʾ������
	//ͼ��
	//�ı�
	public ArrayList<TextBasicFeature> textBFS;
	public ArrayList<TextSemanticFeature>textSFS;

	
	private String type=null;//��ǰ����ķ�Χ
	public XMLToString()
	{
		
	}
	public XMLToString(String type)
	{
		this.type=type;
	}
	
	//Ϊ��ͨ����·�õ�BF 
	
	public void setBF(String parenttype,String filepath)
	{
		try
		{
			if(parenttype.equals("ͼ��"))
			{
				if(BFS==null)
					BFS=parseImageBF(true,filepath);
				else// ���ԭ�������� �����
				{
					ArrayList<ImageBFObj> temp=parseImageBF(true,filepath);
					for(int i=0;i<temp.size();i++)
					   BFS.add(temp.get(i));
				}
			}
			else if(parenttype.equals("�ı�"))
			{
				if(textBFS==null)
					textBFS=parseTextBF(true,filepath);
				else// ���ԭ�������� �����
				{
					ArrayList<TextBasicFeature> temp=parseTextBF(true,filepath);
					for(int i=0;i<temp.size();i++)
						textBFS.add(temp.get(i));
				}
			}
		}catch(SAXException e)
		{
			e.printStackTrace();
		}
		catch(ParserConfigurationException ee)
		{
			ee.printStackTrace();
		}
		catch(FileNotFoundException r)
		{
			r.printStackTrace();
		}
		catch(IOException u)
		{
			u.printStackTrace();
		}
	}
	//ͨ����·�õ�SF
	public void setSF(String parenttype,String filepath)
	{
		try
		{  
				if(parenttype.equals("ͼ��"))
			  {
				if(SFS==null)
					SFS=parseImageSF(true,filepath);
				else
				{
					ArrayList<ImageSemanticFeature> temp=parseImageSF(true,filepath);
					for(int i=0;i<temp.size();i++)
					   SFS.add(temp.get(i));
				}
			}
			else if(parenttype.equals("�ı�"))
			{
				if(textSFS==null)
					textSFS=parseTextSF(true,filepath);
				else
				{
					ArrayList<TextSemanticFeature> temp=parseTextSF(true,filepath);
					for(int i=0;i<temp.size();i++)
						textSFS.add(temp.get(i));
				}
			}
		}catch(SAXException e)
		{
			e.printStackTrace();
		}
		catch(ParserConfigurationException ee)
		{
			ee.printStackTrace();
		}
		catch(FileNotFoundException r)
		{
			r.printStackTrace();
		}
		catch(IOException u)
		{
			u.printStackTrace();
		}
	}
	//����BF��XML�����ļ���·��,��������
	
	//����BF��XML�����ļ���·��,��������
	//filepath��XML���ļ�·��
	/*
	 * flag:true�ļ�·��
	 * flase :�ļ�����
	 */
	public ArrayList<ImageBFObj>  parseImageBF(boolean flag,String filepath ) throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		//�ļ���� 
 //�����ǽ���BF �ѻ�������XML���ı���ȡ�ŵ� ����һ������
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
   
	   factory.setIgnoringElementContentWhitespace(true);
	   DocumentBuilder builder = factory.newDocumentBuilder();
	   Document doc;
	   if(flag)//�ļ�·��
	   {
		InputStream is = new FileInputStream(filepath);
		 doc = builder.parse(is);
	   }
	   else//�ļ�����
	   {
		   ByteArrayInputStream bytein = new ByteArrayInputStream(filepath.getBytes());
		    doc = builder.parse(bytein);
	   }
		Element root = doc.getDocumentElement();// �����ĵ��ĸ�Ԫ��		   
		NodeList childrenlevel1 = root.getChildNodes();
		// �ж���ͼƬ����
		int num = childrenlevel1.getLength();
		ArrayList<ImageBFObj> BFStemp = new ArrayList<ImageBFObj>();
			
		   if(childrenlevel1 != null)
		   {
				for (int i = 0; i < num; i++) 
				{
					Node child = childrenlevel1.item(i);// �����Ӻ���
                     
					if(child.getNodeType()==Node.ELEMENT_NODE)
					{
						
						Element childElement = (Element) child;
						// �ýڵ�����Դ�ӡ
						NamedNodeMap attributes = childElement.getAttributes();
						for (int j = 0; j < attributes.getLength(); j++) 
						{
							Node attribute = attributes.item(j);
							String name = attribute.getNodeName();
							String value = attribute.getNodeValue();
//     						System.out.println(name + ":" + value);
						}
						ImageBFObj BF = new ImageBFObj();
				   for(Node node=child.getFirstChild();node!=null;node=node.getNextSibling())
					{
						// �����Ե��ӽڵ�
					   if(node.getNodeType()==Node.ELEMENT_NODE)
					   {
						   
						   if(node.getNodeName().equals("bfid"))
						   {
							   String vaa;
							   if(node.hasChildNodes())
							   {
							       vaa = node.getFirstChild().getNodeValue();
							      
							   }
							   else
							   {
								   vaa = "";
							   }
//							   int iw = Integer.parseInt(vaa);
								BF.setBfid(vaa);
								
						   }
						   else if(node.getNodeName().equals("date"))
						   {
							   String vaa;
							   int year;
							   int month;
							   int day;
							   if(node.hasChildNodes())
							   {
							      vaa = node.getFirstChild().getNodeValue();
							   BF.setFiledate(vaa);
								String[] str = vaa.split("\\.");
								 year = 1900;
								 month = 1;
								 day = 0;
								if (str.length >= 3) {
									year = Integer.parseInt(str[0]);
									month = Integer.parseInt(str[1]);
									day = Integer.parseInt(str[2]);
								}
							   }
							   else
							   {
								   year = 0;
								   month =0;//?1
								   day = 0;
							   }
							   
								Calendar dt = new GregorianCalendar(year,month - 1, day);							
//								BF.setdate(dt);
						   }
						   else if(node.getNodeName().equals("filepath"))
						   {
							   String vaa;
							   if(node.hasChildNodes())
							   {
								   vaa = node.getFirstChild().getNodeValue();
							   }
							   else
							   {
								   vaa="";
							   }
								BF.setFilepath(vaa);
								
						   }
						   else if(node.getNodeName().equals("filename"))
						   {
							   String vaa;
							   if(node.hasChildNodes())
							   {
								   vaa = node.getFirstChild().getNodeValue();
							   }
							   else
							   {
								   vaa="";
							   }
								BF.setFilename(vaa);
								
						   }
						   else if(node.getNodeName().equals("filesize"))
						   {
							   String vaa;
							   if(node.hasChildNodes())
							   {
								   vaa = node.getFirstChild().getNodeValue();
							   }
							   else
							   {
								   vaa="";
							   }
								BF.setFilesize(vaa);
								
						   }
						   else if(node.getNodeName().equals("author"))
						   {
							   String vaa;
							   if(node.hasChildNodes())
							   {
							      vaa = node.getFirstChild().getNodeValue();
							   }
							   else
							   {
								   vaa="";
							   }
								BF.setFileauthor(vaa);
						   }
					  }
				  }
				   BFStemp.add(BF);
				}
				
              }
            }
		   return BFStemp;
	 }
	
	//�����ı���BF��XML�ַ���
	/*
	 * flag:true:���ļ�·�� false �ļ�����
	 */
	public ArrayList<TextBasicFeature> parseTextBF(boolean flag,String xmlstring)throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		
		
		
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   
		   factory.setIgnoringElementContentWhitespace(true);
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   Document doc;
		 //�ļ�·��
		   if(flag)
		   {
			   InputStream is = new FileInputStream(xmlstring);
				 doc = builder.parse(is);
		   }
		   else
		   {
		   //�ļ�����
			   ByteArrayInputStream bytein = new ByteArrayInputStream(xmlstring.getBytes());
			    doc = builder.parse(bytein);
		   }
		   	Element root = doc.getDocumentElement();// �����ĵ��ĸ�Ԫ��		   
			NodeList childrenlevel1 = root.getChildNodes();
			// �ж���ͼƬ����
			int num = childrenlevel1.getLength();
			ArrayList<TextBasicFeature> BFStemp = new ArrayList<TextBasicFeature>();
				
			if(childrenlevel1 != null)
			   {
					for (int i = 0; i < num; i++) 
					{
						Node child = childrenlevel1.item(i);// �����Ӻ���
	                     
						if(child.getNodeType()==Node.ELEMENT_NODE)
						{
							
							Element childElement = (Element) child;
							// �ýڵ�����Դ�ӡ
							NamedNodeMap attributes = childElement.getAttributes();
							for (int j = 0; j < attributes.getLength(); j++) 
							{
								Node attribute = attributes.item(j);
								String name = attribute.getNodeName();
								String value = attribute.getNodeValue();
//	     						System.out.println(name + ":" + value);
							}
							TextBasicFeature BF = new TextBasicFeature();
					   for(Node node=child.getFirstChild();node!=null;node=node.getNextSibling())
						{
							// �����Ե��ӽڵ�
						   if(node.getNodeType()==Node.ELEMENT_NODE)
						   {
							   
							   if(node.getNodeName().equals("bfid"))
							   {
								   String vaa;
								   if(node.hasChildNodes())
								   {
								       vaa = node.getFirstChild().getNodeValue();
								      
								   }
								   else
								   {
									   vaa = "";
								   }
//								   int iw = Integer.parseInt(vaa);
									BF.setBfid(vaa);
									
							   }
							   else if(node.getNodeName().equals("date"))
							   {
								   String vaa;
								   int year;
								   int month;
								   int day;
								   if(node.hasChildNodes())
								   {
								      vaa = node.getFirstChild().getNodeValue();
								   BF.setStrDate(vaa);
									String[] str = vaa.split("\\.");
									 year = 1900;
									 month = 1;
									 day = 0;
									if (str.length >= 3) {
										year = Integer.parseInt(str[0]);
										month = Integer.parseInt(str[1]);
										day = Integer.parseInt(str[2]);
									}
								   }
								   else
								   {
									   year = 0;
									   month =0;//?1
									   day = 0;
								   }
								   
									Calendar dt = new GregorianCalendar(year,month - 1, day);							
									BF.setDate(dt);
							   }
							   else if(node.getNodeName().equals("filepath"))
							   {
								   String vaa;
								   if(node.hasChildNodes())
								   {
									   vaa = node.getFirstChild().getNodeValue();
								   }
								   else
								   {
									   vaa="";
								   }
									BF.setFilePath(vaa);
									
							   }
							   else if(node.getNodeName().equals("filename"))
							   {
								   String vaa;
								   if(node.hasChildNodes())
								   {
									   vaa = node.getFirstChild().getNodeValue();
								   }
								   else
								   {
									   vaa="";
								   }
									BF.setFileName(vaa);
									
							   }
							   else if(node.getNodeName().equals("filesize"))
							   {
								   String vaa;
								   if(node.hasChildNodes())
								   {
									   vaa = node.getFirstChild().getNodeValue();
								   }
								   else
								   {
									   vaa="";
								   }
									BF.setFileSize(vaa);
									
							   }
							   else if(node.getNodeName().equals("author"))
							   {
								   String vaa;
								   if(node.hasChildNodes())
								   {
								      vaa = node.getFirstChild().getNodeValue();
								   }
								   else
								   {
									   vaa="";
								   }
									BF.setAuthor(vaa);
							   }
						  }
					  }
					   BFStemp.add(BF);
					}
					
	              }
	            }
			   return BFStemp;
		
	}
	
	//����SF��XML�����ļ�,��������
	public  ArrayList<ImageSemanticFeature>  parseImageSF(boolean flag,String xmlstring ) throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		 ArrayList<ImageSemanticFeature> SFStemp = new  ArrayList<ImageSemanticFeature>();
		 
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   factory.setIgnoringElementContentWhitespace(true);
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   
//			InputStream is = new FileInputStream(filename);
//			Document doc = builder.parse(is);
			Document doc;
			 //�ļ�·��
			   if(flag)
			   {
				   InputStream is = new FileInputStream(xmlstring);
					 doc = builder.parse(is);
			   }
			   else
			   {
			   //�ļ�����
				   ByteArrayInputStream bytein = new ByteArrayInputStream(xmlstring.getBytes());
				    doc = builder.parse(bytein);
			   }
			   
			Element root = doc.getDocumentElement();// �����ĵ��ĸ�Ԫ��		   
			NodeList childrenlevel1 = root.getChildNodes();
			// �ж���ͼƬ����SF
			int num = childrenlevel1.getLength();
			if(childrenlevel1 != null)
			   {
					for (int i = 0; i < num; i++) 
					{
						Node child = childrenlevel1.item(i);// �����Ӻ���
						if(child.getNodeType()==Node.ELEMENT_NODE)
						{
							// �ýڵ�����Դ�ӡ
							NamedNodeMap attributes = ((Element)child).getAttributes();
							for (int j = 0; j < attributes.getLength(); j++) 
							{
								Node attribute = attributes.item(j);
								String name = attribute.getNodeName();
								String value = attribute.getNodeValue();
//								System.out.println(name + ":" + value);
							}
							ImageSemanticFeature SF = new ImageSemanticFeature();
							
					     for(Node node=child.getFirstChild();node!=null;node=node.getNextSibling())
						{
							// �����Ե��ӽڵ�
//					    	 System.out.println(node.getNodeName());
						   if(node.getNodeType()==Node.ELEMENT_NODE)
						   {
							  for(int id=0;id<SF.ATTRIBUTENUMBERS;id++)
							  {
								  if(node.getNodeName().equals(SF.getSingleItem(id)))
								  {
									  String key=SF.getSingleItem(id);
									  String value ;
									   if(node.hasChildNodes())
									   {
										   value = node.getFirstChild().getNodeValue();
									   }
									   else
									   {
										   value ="";
									   }
									  SF.set(key, value);
//									  break;
								  }
							  }
							  
							  /*�� ��Ȥ������ �����Ȳ�����
							  if(node.getNodeName().equalsIgnoreCase("ROI"))
							   {
								   NamedNodeMap childAttributes = ((Element)node).getAttributes();
								// �ýڵ�����Դ�ӡ
									for (int j = 0; j < childAttributes.getLength(); j++) 
									{
										Node attribute = childAttributes.item(j);
										String name = attribute.getNodeName();
										String value = attribute.getNodeValue();
//			     						System.out.println(name + ":" + value);
									}
									ArrayList<LocalDiagnosis> locals = new ArrayList<LocalDiagnosis>();
									for(Node child2 = node.getFirstChild();child2 != null;child2=node.getNextSibling())
									{
										LocalDiagnosis local = new LocalDiagnosis();
										if(child2.getNodeType()== Node.ELEMENT_NODE)
										{
											if(child2.getNodeName().equalsIgnoreCase("ROI_X"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
												   float ft = Float.parseFloat(vaa);
											      local.setROI_X(ft);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Y"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
												   float ft = Float.parseFloat(vaa);
											      local.setROI_Y(ft);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Width"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
												   float ft = Float.parseFloat(vaa);
											       local.setROI_Width(ft);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Height"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
												   float ft = Float.parseFloat(vaa);
											       local.setROI_Height(ft);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Annotation_Date"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
											       local.setROI_Annotation_Date(vaa);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Annotation_Time"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
											       local.setROI_Annotation_Time(vaa);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Annotation_Area"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
											       local.setROI_Annotation_Area(vaa);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Diagnostic_Info"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
											       local.setROI_Diagnostic_Info(vaa);
											}
											else if(child2.getNodeName().equalsIgnoreCase("ROI_Annotator"))
											{
												   String vaa = node.getFirstChild().getNodeValue();
											       local.setROI_Annotator(vaa);
											}
											locals.add(local);
										}
										
									}
									SF.setDiagnostic_Info(locals);
								
							   }
							   */	
						  }
					   }
					   
					   SFStemp.add(SF);
					}
	              }
	           }
			
		 return SFStemp;
	}
	
	/*
	 * ����XMLString:��ΪXML��STRING
	 */
	public ArrayList<TextSemanticFeature> parseTextSF(boolean flag,String  xmlstring)throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		ArrayList<TextSemanticFeature> SFStemp = new  ArrayList<TextSemanticFeature>();
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   factory.setIgnoringElementContentWhitespace(true);
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   Document doc;
			 //�ļ�·��
			   if(flag)
			   {
				   InputStream is = new FileInputStream(xmlstring);
					 doc = builder.parse(is);
			   }
			   else
			   {
			   //�ļ�����
				   ByteArrayInputStream bytein = new ByteArrayInputStream(xmlstring.getBytes());
				    doc = builder.parse(bytein);
			   }
			
			Element root = doc.getDocumentElement();// �����ĵ��ĸ�Ԫ��		   
			NodeList childrenlevel1 = root.getChildNodes();
			// �ж���ͼƬ����SF
			int num = childrenlevel1.getLength();
			if(childrenlevel1 != null)
			   {
					for (int i = 0; i < num; i++) 
					{
						Node child = childrenlevel1.item(i);// �����Ӻ���
						if(child.getNodeType()==Node.ELEMENT_NODE)
						{
							// �ýڵ�����Դ�ӡ
							NamedNodeMap attributes = ((Element)child).getAttributes();
							for (int j = 0; j < attributes.getLength(); j++) 
							{
								Node attribute = attributes.item(j);
								String name = attribute.getNodeName();
								String value = attribute.getNodeValue();
//								System.out.println(name + ":" + value);
							}
							TextSemanticFeature SF = new TextSemanticFeature();
							
					     for(Node node=child.getFirstChild();node!=null;node=node.getNextSibling())
						{
							// �����Ե��ӽڵ�
//					    	 System.out.println(node.getNodeName());
						   if(node.getNodeType()==Node.ELEMENT_NODE)
						   {
							  for(int id=0;id<SF.ATTRIBUTENUMBERS;id++)
							  {
								  if(node.getNodeName().equals(SF.getSingleItem(id)))
								  {
									  String key=SF.getSingleItem(id);
									  String value ;
									   if(node.hasChildNodes())
									   {
										   value = node.getFirstChild().getNodeValue();
									   }
									   else
									   {
										   value ="";
									   }
									  SF.set(key, value);
								  }
							  }
						   }
						   SFStemp.add(SF);
						}
						}
					}
			   }
			return SFStemp;

	}
	
	
	/*
	 * setTextBF setTextSF����TEXT��ȡ BF SF
	 */
	public ArrayList<TextBasicFeature> setTextBF(String aresult) throws SAXException, ParserConfigurationException ,FileNotFoundException,IOException
	{
		String tempBf="<?xml version=\"1.0\" encoding=\"gbk\"?> <basicfeature>";
		if(aresult!=null)
 		{
		 			tempBf= tempBf+aresult;
	 		    
 		  }
		else
		{
				System.out.println("��������ֵΪ ��!");
		}
		tempBf=tempBf+"</basicfeature>";
			
		//�����ļ�
		ArrayList<TextBasicFeature> bf=parseTextBF(false,tempBf);
	 
		return bf;
	}
}
