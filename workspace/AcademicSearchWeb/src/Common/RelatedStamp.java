package Common;

import java.util.HashMap;

public class RelatedStamp {

	public String[] AUDR_DATAE = {"image","text","audio","video"}; 
	public String[] AUDR_DATAC = {"������ͼ��","�������ı�","��������Ƶ","��������Ƶ"};
	
	public String[][] DATA_CATALOG = new String[][]{
			{"ȫ��", "�ƾ�","����","����", "����", "�Ƽ�", "����", "����", "�Ļ�", "����", "����"},
			{"ȫ��", "����","ֲ��","����", "�羰", "�", "����", "ʳ��", "ҽѧ"},
			{"ȫ��", "����", "������", "�ŵ�", "ҡ��", "��ʿ", "���&���", "Ӱ��&����", "�ٶ�&�ڽ�", "����"},
			{"ȫ��", "��ҵ", "����", "����", "����", "����", "��ҵ�ͼ���", "����Ļ�", "����"}
	};
	public String[][] DATA_CATALOGE = new String[][]{
			{"text", "finance","health","education", "military", "science", "tour", "sport", "culture", "consumption", "government"},
			{"image", "animal","plant","instrument", "scene", "activity", "structure", "food", "medicine"},
			{"audio", "Audio_pop", "Audio_light_music", "Audio_classic", "Audio_rock_roll", "Audio_jazz", "Audio_country_folk", "Audio_movie_animation", "Audio_child_religion", "Audio_other"},
			{"All", "Video_Business", "Video_Entertainment", "Video_Health", "Video_News", "Video_Education-and-Culture", "Video_Science-and-Technology", "Video_Society-and-Life", "Video_Sports"}
	};
	
	public String[][] SEARCH_ITEM =  new String[][]{
			{"�ؼ���"},
			{"����", "�ؼ���", "����"},
			{"��Ƶ����", "�ؼ���", "����", "ר��", "����"},
			{"����", "�ؼ���", "����", "��Ļ"}
	};
	String keys[][] = {
			{"Keyword"},
			{"Subject","Keywords","Description"},
			{"Keyword", "AudioName", "Singer", "Album","Theme"},
			{"theme", "keyword", "describe", "subtitle"},
			};
	
	public RelatedStamp() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
