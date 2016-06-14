package utils;

import java.util.List;

public class Page {

	private int totalRecords; // Ŀǰ���ݿ��м�¼����
	private int recordsOfSinglePage; // ��ҳ��ʾ��¼��
	private int totalPages; // �ܹ���ҳ��
	private int buttonsOfSinglePage; // ��ҳ��ʾ��ҳ��ť��
	private int currentPageId; // ��ǰ��ʾҳ��
	private int prevPageId; // ��һҳҳ�룬û����һҳ-1
	private int nextPageId; // ��һҳҳ��û����һҳ-1
	private int startRecordId; // ��ʼ��¼��id
	private int endRecordId; // ������¼��id
	private int startPageId; // ��ʼ��ҳ��ť��ҳid
	private int endPageId; // ������ҳ��ť��ҳid

	private List pageRecords; 
	
	public Page(int totalRecords, int recordOfSinglePage,
			int buttonsOfSinglePage, int currentPageId) {
		this.totalRecords = totalRecords;
		this.recordsOfSinglePage = recordOfSinglePage;
		this.totalPages = this.totalRecords < this.recordsOfSinglePage ? 1
				: this.totalRecords % this.recordsOfSinglePage == 0 ? this.totalRecords
						/ this.recordsOfSinglePage
						: this.totalRecords / this.recordsOfSinglePage + 1;
		this.buttonsOfSinglePage = buttonsOfSinglePage;
		this.currentPageId = (currentPageId < 1 || currentPageId > this.totalPages) ? -1 : currentPageId;
		this.prevPageId = this.currentPageId <= 1 ? -1 : this.currentPageId - 1;
		this.nextPageId = this.currentPageId >= this.totalPages ? -1 : this.totalPages + 1;
		this.startRecordId = (this.currentPageId - 1) * this.recordsOfSinglePage + 1;
		this.endRecordId = this.startRecordId + this.recordsOfSinglePage - 1;
		if (this.totalPages <= this.buttonsOfSinglePage) {
			this.startPageId = 1;
			this.endPageId = this.totalPages;
		} else {
			this.startPageId = this.currentPageId - this.buttonsOfSinglePage;
			this.endPageId = this.currentPageId + this.buttonsOfSinglePage;
			if (this.startPageId < 1) {
				this.startPageId = 1;
				this.endPageId = this.buttonsOfSinglePage;
			}
			if (this.endPageId > this.totalPages) {
				this.startPageId = this.endPageId - this.buttonsOfSinglePage - 2;
				this.endPageId = this.totalPages;
			}
		}
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getRecordsOfSinglePage() {
		return recordsOfSinglePage;
	}

	public void setRecordsOfSinglePage(int recordsOfSinglePage) {
		this.recordsOfSinglePage = recordsOfSinglePage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getButtonsOfSinglePage() {
		return buttonsOfSinglePage;
	}

	public void setButtonsOfSinglePage(int buttonsOfSinglePage) {
		this.buttonsOfSinglePage = buttonsOfSinglePage;
	}

	public int getCurrentPageId() {
		return currentPageId;
	}

	public void setCurrentPageId(int currentPageId) {
		this.currentPageId = currentPageId;
	}

	public int getPrevPageId() {
		return prevPageId;
	}

	public void setPrevPageId(int prevPageId) {
		this.prevPageId = prevPageId;
	}

	public int getNextPageId() {
		return nextPageId;
	}

	public void setNextPageId(int nextPageId) {
		this.nextPageId = nextPageId;
	}

	public int getStartRecordId() {
		return startRecordId;
	}

	public void setStartRecordId(int startRecordId) {
		this.startRecordId = startRecordId;
	}

	public int getEndRecordId() {
		return endRecordId;
	}

	public void setEndRecordId(int endRecordId) {
		this.endRecordId = endRecordId;
	}

	public int getStartPageId() {
		return startPageId;
	}

	public void setStartPageId(int startPageId) {
		this.startPageId = startPageId;
	}

	public int getEndPageId() {
		return endPageId;
	}

	public void setEndPageId(int endPageId) {
		this.endPageId = endPageId;
	}
	
	public List getPageRecords() {
		return pageRecords;
	}

	public void setPageRecords(List pageRecords) {
		this.pageRecords = pageRecords;
	}

	@Override
	public String toString() {
		return String
				.format("Page [totalRecords=%s, recordsOfSinglePage=%s, totalPages=%s, buttonsOfSinglePage=%s, currentPageId=%s, prevPageId=%s, nextPageId=%s, startRecordId=%s, endRecordId=%s, startPageId=%s, endPageId=%s, pageRecords=%s]",
						totalRecords, recordsOfSinglePage, totalPages,
						buttonsOfSinglePage, currentPageId, prevPageId,
						nextPageId, startRecordId, endRecordId, startPageId,
						endPageId, pageRecords);
	}
	
}
