package ksmybatis.page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Pageable {

	private int currentPage = 1;
	private int rowPerPage = 10;
	private int offset;
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		offsetProcess();
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
		offsetProcess();
	}
	public void offsetProcess() {
		
		int offset = (currentPage - 1) * rowPerPage;
		
		this.offset = offset;
	}
}






















