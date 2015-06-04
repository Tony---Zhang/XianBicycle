package com.thoughtworks.xianbicycle.model.request;

import java.util.List;

public class Search {

    private String term;
    private Location location;
    private List<String> ids;

    public Search(String term) {
        this.term = term;
    }

    public Search(Location location) {
        this.location = location;
    }

    public Search(List<String> ids) {
        this.ids = ids;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
