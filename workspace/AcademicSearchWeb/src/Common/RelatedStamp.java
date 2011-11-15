package Common;

import java.util.HashMap;

public class RelatedStamp {

	public String[] AUDR_DATAE = {"image","text","audio","video"}; 
	public String[] AUDR_DATAC = {"关联到图像","关联到文本","关联到音频","关联到视频"};
	
	public String[][] DATA_CATALOG = new String[][]{
			{"全部", "财经","健康","教育", "军事", "科技", "旅游", "体育", "文化", "消费", "政府"},
			{"全部", "动物","植物","仪器", "风景", "活动", "建筑", "食物", "医学"},
			{"全部", "流行", "轻音乐", "古典", "摇滚", "爵士", "乡村&民歌", "影视&动漫", "少儿&宗教", "其他"},
			{"全部", "商业", "娱乐", "健康", "新闻", "教育", "工业和技术", "社会文化", "体育"}
	};
	public String[][] DATA_CATALOGE = new String[][]{
			{"text", "finance","health","education", "military", "science", "tour", "sport", "culture", "consumption", "government"},
			{"image", "animal","plant","instrument", "scene", "activity", "structure", "food", "medicine"},
			{"audio", "Audio_pop", "Audio_light_music", "Audio_classic", "Audio_rock_roll", "Audio_jazz", "Audio_country_folk", "Audio_movie_animation", "Audio_child_religion", "Audio_other"},
			{"All", "Video_Business", "Video_Entertainment", "Video_Health", "Video_News", "Video_Education-and-Culture", "Video_Science-and-Technology", "Video_Society-and-Life", "Video_Sports"}
	};
	
	public String[][] SEARCH_ITEM =  new String[][]{
			{"关键字"},
			{"主题", "关键字", "描述"},
			{"音频名称", "关键字", "歌手", "专辑", "主题"},
			{"主题", "关键字", "描述", "字幕"}
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
