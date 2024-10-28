package ksmybatis.page;

import java.util.List;

import lombok.Data;

@Data
public class PageInfo<T> {
	private Pageable pageable;
	private int rowCount;
	private List<T> contents;
	private int currentPage;
	private int lastPage;
	private int startPageNum;
	private int endPageNum;
	
	public PageInfo(List<T> contents, Pageable pageable, int rowCount) {
		this.contents = contents;
		this.pageable = pageable;
		this.rowCount = rowCount;
		pageProcess();
	}
	
	public void pageProcess() {
		int currentPage = pageable.getCurrentPage();
		int rowPerPage = pageable.getRowPerPage();
		
		int lastPage = (int) Math.ceil((double)rowCount/rowPerPage);
		
		/* 페이지 번호 */
		int startPageNum = 1;
		int endPageNum = 10;
		
		endPageNum = lastPage < 10 ? lastPage : endPageNum;
		
		if(currentPage > 6 && endPageNum > 9) {
			startPageNum = currentPage - 5;
			endPageNum = currentPage + 4;
			if(endPageNum >= lastPage) {
				startPageNum = lastPage - 9;
				endPageNum = lastPage;
			}
		}
		
		this.currentPage = currentPage;
		this.lastPage = lastPage;
		this.startPageNum = startPageNum;
		this.endPageNum = endPageNum;
	}
	
}











