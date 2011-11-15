package AUDRwebJavaBeans;

import java.util.ArrayList;
import java.util.HashMap;

import AUDRcopyCS.getAddSFBF;

import sys.TextQueryResult;

public class TextItem {
	
	TextQueryResult tqr;
	TextModel tm;
	TextBasicFeature tbf;
	TextSemanticFeature tsf;
	
	public TextQueryResult getTqr() {
		return tqr;
	}
	public void setTqr(TextQueryResult tqr) {
		this.tqr = tqr;
	}
	public TextModel getTm() {
		return tm;
	}
	public void setTm(TextModel tm) {
		this.tm = tm;
	}
	public TextBasicFeature getTbf() {
		return tbf;
	}
	public void setTbf(TextBasicFeature tbf) {
		this.tbf = tbf;
	}
	public TextSemanticFeature getTsf() {
		return tsf;
	}
	public void setTsf(TextSemanticFeature tsf) {
		this.tsf = tsf;
	}
	
	public ArrayList<TextItem> toList(int begin,int end,String path,TextQueryResult[] trs)
	{
		if(begin<0||end<begin)
			return null;
		if(begin>trs.length)
			return null;
		if(trs==null||trs.length==0)
			return null;
		
		if(end>trs.length)
			end=trs.length;
		
		
		ArrayList<TextItem> list = new ArrayList<TextItem>();
		
		ArrayList<String> keys = new ArrayList<String>();
		
		HashMap<String ,TextModel> tmMap=new HashMap<String, TextModel>();
		HashMap<String ,TextBasicFeature> tbfMap=new HashMap<String, TextBasicFeature>();
		HashMap<String ,TextSemanticFeature> tsfMap=new HashMap<String, TextSemanticFeature>();
		
		for(int i= begin;i< end;i++)
		{
			keys.add(trs[i].getId());
		}
		
		 getAddSFBF S = new getAddSFBF(tmMap,tbfMap,tsfMap);
		//�ѽ���ŵ� LIST TEXTBF TEXTSF
		 S.AddSFBF(0,(String[])keys.toArray(new String[0]) ,trs,path);
		 //���±�������ͨ��results.id ��Ϊ��ֵ��
		//results��list��textBF��textSF
		
		for (int i = begin; i < end; i++) {
			TextItem ttm = new TextItem();
			ttm.setTqr(trs[i]);
			ttm.setTm(tmMap.get(trs[i].getId()));
			ttm.setTbf(tbfMap.get(trs[i].getId()));
			ttm.setTsf(tsfMap.get(trs[i].getId()));
			list.add(ttm);
		}
		return list;
		
	}
}