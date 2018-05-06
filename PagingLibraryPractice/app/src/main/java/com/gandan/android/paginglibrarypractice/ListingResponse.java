package com.gandan.android.paginglibrarypractice;

import java.util.List;

/**
 * Created by XPS on 2018-05-07.
 */

public class ListingResponse {

    private List<RedditChildrenResponse> children;
    private String after;
    private String before;

    public List<RedditChildrenResponse> getChildren() {
        return children;
    }

    public void setChildren(List<RedditChildrenResponse> children) {
        this.children = children;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }
}
