package domain;

import java.util.List;

public class Page {

	private int totalRows; // һ��������
	private int totalPages; // һ������ҳ
	private int pageRange; // ��ҳ��ť���
	private int pageRecords; // ÿҳ��¼��
	private int currentPageId; // ��ǰҳ��
	private int prevPageId; // ��һҳ
	private int nextPageId; // ��һҳ
	private int startIndex; // ��ʼ��¼id
	private int endIndex; // ������¼id
	private int startPageId; // ��ҳ��ť��ʼҳid
	private int endPageId; // ��ҳ��ť����ҳid

	private List records;

	public Page(int totalRows, int pageRange, int pageRecords, int currentPageId) {
		this.totalRows = totalRows;
		this.pageRecords = pageRecords;
		this.totalPages = this.totalRows < this.pageRecords ? 1
				: this.totalRows % this.pageRecords == 0 ? this.totalRows
						/ this.pageRecords : this.totalRows / this.pageRecords
						+ 1;
		this.pageRange = pageRange;
		this.currentPageId = currentPageId;
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
