package domain;

import java.util.List;

public class Page {

	private int totalRows; // 一共多少行
	private int totalPages; // 一共多少页
	private int pageRange; // 翻页按钮跨度
	private int pageRecords; // 每页记录数
	private int currentPageId; // 当前页码
	private int prevPageId; // 上一页
	private int nextPageId; // 下一页
	private int startIndex; // 开始记录id
	private int endIndex; // 结束记录id
	private int startPageId; // 翻页按钮开始页id
	private int endPageId; // 翻页按钮结束页id

	private List records;

	public Page(int totalRows, int pageRange, int pageRecords, int currentPageId) {
		this.totalRows = totalRows;
		this.pageRecords = pageRecords;
		this.totalPages = this.totalRows < this.pageRecords ? 1
				: this.totalRows % this.pageRecords == 0 ? this.totalRows
						/ this.pageRecords : this.totalRows / this.pageRecords
						+ 1;
		this.pageRange = pageRange;
		this.currentPageId = currentPageId < 0 ? 1 : currentPageId > this.totalPages ? this.totalPages : currentPageId;
		this.prevPageId = this.currentPageId == 1 ? -1 : this.currentPageId - 1;
		this.nextPageId = this.currentPageId == this.totalPages ? -1
				: this.currentPageId + 1;
		this.startIndex = (this.currentPageId - 1) * this.pageRecords + 1;
		this.endIndex = this.startIndex + this.pageRecords - 1;
		if (this.totalPages <= this.pageRange) {
			this.startPageId = 1;
			this.endPageId = this.totalPages;
		} else {
			this.startPageId = this.currentPageId - this.pageRange;
			this.endPageId = this.currentPageId + this.pageRange;
			if (this.startPageId < 1) {
				this.startPageId = 1;
				this.endPageId = this.pageRange;
			}
			if (this.endPageId > this.totalPages) {
				this.startPageId = this.endPageId - this.pageRange - 1;
				this.endPageId = this.totalPages;
			}
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageRange() {
		return pageRange;
	}

	public int getPageRecords() {
		return pageRecords;
	}

	public int getCurrentPageId() {
		return currentPageId;
	}

	public int getPrevPageId() {
		return prevPageId;
	}

	public int getNextPageId() {
		return nextPageId;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public int getStartPageId() {
		return startPageId;
	}

	public int getEndPageId() {
		return endPageId;
	}

	public List getRecords() {
		return records;
	}

	public void setPageRange(int pageRange) {
		this.pageRange = pageRange;
	}

	public void setPageRecords(int pageRecords) {
		this.pageRecords = pageRecords;
	}

	public void setRecords(List records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Page [currentPageId=" + currentPageId + ", endIndex="
				+ endIndex + ", endPageId=" + endPageId + ", nextPageId="
				+ nextPageId + ", pageRange=" + pageRange + ", pageRecords="
				+ pageRecords + ", prevPageId=" + prevPageId + ", records="
				+ records + ", startIndex=" + startIndex + ", startPageId="
				+ startPageId + ", totalPages=" + totalPages + ", totalRows="
				+ totalRows + "]";
	}
	
}
