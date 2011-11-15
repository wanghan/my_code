package AUDRwebJavaBeans;
/*
 * �ı���ʾʱ�õ���
 */

public class Page {

	//��Ϣ����
	private int pagetotal;
	//ÿҳ��ʾ���������Ǹ�
	private int pageSize = 10;
	//��ҳ��
	private int pageCount;
	//��ǰҳ
	private int currentPage;

	
	private int showStartPage;
	private int showEndPage;
	
	public int getShowStartPage()
	{
		if( this.getPageCount() > 10 )
		{
			//��ҳ
			if( this.getCurrentPage() == 0 )
			{
				
				this.setShowStartPage(0);
				this.setShowEndPage(10);
			}
			
			else if( this.getCurrentPage() + 10 <= this.getPageCount() )
			{
				this.setShowEndPage( this.getCurrentPage() + 10 );
				if( this.getShowEndPage() - 20 >= 0 )
				{
					this.setShowStartPage(  this.getShowEndPage() - 20 );
				}else{
					this.setShowStartPage( 0 );
				}
				
			}
			else if(this.getCurrentPage() + 10 > this.getPageCount())
			{
				this.setShowEndPage( this.getPageCount() );
				this.setShowStartPage( this.getPageCount() - 10 );
			}
			//���һҳ
			else if( this.getCurrentPage() == this.getPageCount() - 1 )
			{
				this.setShowEndPage( this.getPageCount() );
				this.setShowStartPage( this.getShowEndPage() - 20 );
			}
		}else
		{
			this.setShowStartPage(0);
			this.setShowEndPage( this.getPageCount() );
		}
		
		
		return showStartPage;
	}
	public void setShowStartPage(int showStartPage) 
	{
		this.showStartPage = showStartPage;
	}
	public int getShowEndPage() 
	{
		return showEndPage;
	}
	public void setShowEndPage(int showEndPage) {
		this.showEndPage = showEndPage;
	}
	public int getPagetotal() {
		return pagetotal;
	}
	public void setPagetotal(int pagetotal) {
		this.pagetotal = pagetotal;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() 
	{
		if( 0 == pagetotal % pageSize )
		{
			return pagetotal / pageSize ;
		}
		else
		{
			return pagetotal / pageSize + 1;
		}
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageCount() {
		this.pageCount = this.getPageCount();
	}
	
}
