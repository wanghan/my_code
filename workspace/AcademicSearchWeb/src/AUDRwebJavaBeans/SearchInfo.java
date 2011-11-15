package AUDRwebJavaBeans;

public class SearchInfo extends TextModel{

	//搜索内容
	private String searchText;
	//搜索耗费时间
	private long searchTime;
	//文本类别
	private int category;
	
	//提交者
	private String textAuthor;
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public long getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(long searchTime) {
		this.searchTime = searchTime;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getTextAuthor() {
		return textAuthor;
	}
	public void setTextAuthor(String textAuthor) {
		this.textAuthor = textAuthor;
	}

	
}
