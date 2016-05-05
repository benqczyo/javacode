package com.benqcz.crm.domain;

import java.util.List;


public class Page {

	private int totalRows;
	private int totalPages;
	private int defaultPageSize;
	private int currentPageId;
	private int prePageId;
	private int nextPageId;
	private int startIndex;
	private int endIndex;
	
	private List<CustomerBean> recoders;
	
	public Page(int totalRows, int pageId) {
		this.defaultPageSize = 10;
		this.totalRows = totalRows;
		this.currentPageId = pageId;
		this.totalPages = this.totalRows / this.defaultPageSize + this.totalRows % this.defaultPageSize;
		this.prePageId = this.currentPageId == 1 ? -1 : this.currentPageId - 1;
		this.nextPageId = this.currentPageId == this.totalPages ? -1 : this.currentPageId + 1;
		this.startIndex = (this.currentPageId - 1) * this.defaultPageSize;
		this.endIndex = this.currentPageId * this.defaultPageSize - 1;
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

	public int getPrePageId() {
		return prePageId;
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
	
	@Override
	public String toString() {
		return "Page [currentPageId=" + currentPageId + ", defaultPageSize="
				+ defaultPageSize + ", endIndex=" + endIndex + ", nextPageId="
				+ nextPageId + ", prePageId=" + prePageId + ", startIndex="
				+ startIndex + ", totalPages=" + totalPages + ", totalRows="
				+ totalRows + "]";
	}

}
