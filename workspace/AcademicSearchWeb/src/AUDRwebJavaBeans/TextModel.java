package AUDRwebJavaBeans;

public class TextModel 
{
	private final static int END_INDEX = 50;
	
	private String wrongString="nothing";
	private String id;//文本的唯一ID
	
	//sf
	private String textType;
	private String title;
	private String subject;
	private String url;//原始的资料来源的RUL
	private String tempUrl=null;//
	
	private String author;
	private String keyword;
	private String remark;
	private String wordCount;
	private String template;
	private String department;
	private String sort;
	private String lastAuthor;
	private String modifyCount;
	private String editTime;
	private String createTime;
	private String printTime;
	
	
	
	//bf
	private String date;
	private String filePath;
	private String fileName;
	private String fileSize;
	private String content;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String au) {
		this.author = au;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWordCount() {
		return wordCount;
	}
	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getLastAuthor() {
		return lastAuthor;
	}
	public void setLastAuthor(String lastAuthor) {
		this.lastAuthor = lastAuthor;
	}
	public String getModifyCount() {
		return modifyCount;
	}
	public void setModifyCount(String modifyCount) {
		this.modifyCount = modifyCount;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPrintTime() {
		return printTime;
	}
	public void setPrintTime(String printTime) {
		this.printTime = printTime;
	}
	
	
	
	
	public String getTextType() {
		return textType;
	}
	public void setTextType(String textType) {
		this.textType = textType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		if( null == title || "".equals( title ))
		{
			if( this.getContent().length() > END_INDEX )
				return this.getContent().trim().substring(0, END_INDEX) + "...";
			else
				return this.getContent().trim();
		}
		else
		{
			if( title.length() > END_INDEX )
				return title.trim().substring(0, END_INDEX) + "..." ;
			else
				return title.trim();
		}
	}
	public void setTitle(String title) {
	
		this.title = title.trim();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content.trim();
	}
	
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getWrongString()
	{
		return this.wrongString;
	}
	//ying
	public String getTempUrl()
	{
		return this.tempUrl;
	}
	public void setTempUrl(String tempurl)
	{
		this.tempUrl=tempurl;
	}
//	public String getModifyTime() {
//		return modifyTime;
//	}
//	public void setModifyTime(String modifyTime) {
//		this.modifyTime = modifyTime;
//	}
}
