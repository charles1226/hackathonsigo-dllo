package com.siigo.invoice.util.search;

import java.util.List;

public class SearcherDataDTO<T> {
	private long currentPage;
	private long count;
	List<T> data;
	
	public SearcherDataDTO(long currentPage, long resultsCount, List<T> data) {
        this.currentPage = currentPage;
        this.count = resultsCount;
        this.data = data;
    }

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
