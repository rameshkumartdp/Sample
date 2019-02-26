package com.oracle.services.spellcheck.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rames on 26-02-2019.
 */
public class ResponseGroup implements Serializable {
    private String groupName;
    private List<Facet> facets;

    public ResponseGroup() {
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Facet> getFacets() {
        return this.facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }

    public String toString() {
        return "ResponseGroup{groupName=\'" + this.groupName + '\'' + ", facets=" + this.facets + '}';
    }
}

