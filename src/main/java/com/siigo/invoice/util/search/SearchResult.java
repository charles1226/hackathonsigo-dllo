package com.siigo.invoice.util.search;


import java.util.List;

public class SearchResult<T> {

    private long resultsCount;
    private List<T> result;

    public SearchResult(long resultsCount, List<T> result) {
        this.resultsCount = resultsCount;
        this.result = result;
    }

    public long getResultsCount() {
        return resultsCount;
    }

    public List<T> getResult() {
        return result;
    }
}
