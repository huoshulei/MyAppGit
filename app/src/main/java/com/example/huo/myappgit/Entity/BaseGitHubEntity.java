package com.example.huo.myappgit.Entity;

import java.util.List;

/**
 * Created by huo on 08/07/16.
 */

public class BaseGitHubEntity<T> {

    /**
     * total_count : 2110203
     * incomplete_results : false
     */

    private boolean incomplete_results;
    private T items;

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public T getItems() {
        return items;
    }

    public void setItems(T items) {
        this.items = items;
    }
}
