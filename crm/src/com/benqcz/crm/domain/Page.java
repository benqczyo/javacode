package com.benqcz.crm.domain;

import java.util.List;


public class Page {

	private int totalRows;
	private int totalPages;
	private int defaultPageSize;
	private int defaultPageRecords;
	private int currentPageId;
	private int prevPageId;
	private int nextPageId;
	private int startIndex;
	private int endIndex;
	private int startPageId;
	private int endPageId;
	
	private List<CustomerBean> recoders;
	
	public Page(int totalRows, int pageId) {
		this.defaultPageSize = 1;
		this.defaultPageRecords = 2;
		this.totalRows = totalRows;
		this.currentPageId = pageId;
		this.totalPages = this.totalRows < this.defaultPageSize ? 1 : this.totalRows % this.defaultPageSize == 0 ? this.totalRows / this.defaultPageSize : this.totalRows / this.defaultPageSize + 1;
		this.prevPageId = this.currentPageId == 1 ? -1 : this.currentPageId - 1;
		this.nextPageId = this.currentPageId == this.totalPages ? -1 : this.currentPageId + 1;
		this.startIndex = (this.currentPageId - 1) * this.defaultPageSize + 1;
		this.endIndex = this.currentPageId * this.defaultPageSize;
		if (this.totalPages <= this.defaultPageRecords) {
			this.startPageId = 1;
			this.endPageId = this.totalPages;
		} else {
			this.startPageId = this.currentPageId - this.defaultPageRecords;
			this.endPageId = this.currentPageId + this.defaultPageRecords;
			if (this.startPageId < 1) {
				this.startPageId = 1;
				this.endPageId = this.defaultPageRecords;
			}
			if (this.endPageId > this.totalPages) {
				this.startPageId = this.endPageId - this.defaultPageRecords - 1;
				this.endPageId = this.totalPages;
			}
		}
	}

	public int getDefaultPageRecords() {
		return defaultPageRecords;
	}

	public void setDefaultPageRecords(int defaultPageRecords) {
		this.defaultPageRecords = defaultPageRecords;
	}

	public int getStartPageId() {
		return startPageId;
	}

	public int getEndPageId() {
		return endPageId;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getDefaultPageSize() {
		return defaultPageSize;
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

	public List<CustomerBean> getRecoders() {
		return recoders;
	}
	
	public void setRecoders(List<CustomerBean> recoders) {
		this.recoders = recoders;
	}

	@Override
	public String toString() {
		return String
				.format(
						"Page [currentPageId=%s, defaultPageSize=%s, endIndex=%s, nextPageId=%s, prePageId=%s, recoders=%s, startIndex=%s, totalPages=%s, totalRows=%s]",
						currentPageId, defaultPageSize, endIndex, nextPageId,
						prevPageId, recoders, startIndex, totalPages, totalRows);
	}

}
