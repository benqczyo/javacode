package com.benqcz.crm.domain;

import java.util.List;


public class Page {

	private int totalRows;
	private int totalPages;
	private int defaultPageSize;
	private int currentPageId;
	private int prevPageId;
	private int nextPageId;
	private int startIndex;
	private int endIndex;
	
	private List<CustomerBean> recoders;
	
	public Page(int totalRows, int pageId) {
		this.defaultPageSize = 2;
		this.totalRows = totalRows;
		this.currentPageId = pageId;
		this.totalPages = this.totalRows < this.defaultPageSize ? 1 : this.totalRows / this.defaultPageSize + this.totalRows % this.defaultPageSize;
		this.prevPageId = this.currentPageId == 1 ? -1 : this.currentPageId - 1;
		this.nextPageId = this.currentPageId == this.totalPages ? -1 : this.currentPageId + 1;
		this.startIndex = (this.currentPageId - 1) * this.defaultPageSize + 1;
		this.endIndex = this.currentPageId * this.defaultPageSize;
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
