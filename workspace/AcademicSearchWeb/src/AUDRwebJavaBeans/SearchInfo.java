package AUDRwebJavaBeans;

public class SearchInfo extends TextModel{

	//��������
	private String searchText;
	//�����ķ�ʱ��
	private long searchTime;
	//�ı����
	private int category;
	
	//�ύ��
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
