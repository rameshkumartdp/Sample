package com.aktway.services.core.response;

/**
 * Created by rames on 26-02-2019.
 */
import java.io.Serializable;

public class Facet implements Serializable {
    private String facetName;
    private long facetCount;

    public Facet() {
    }

    public String getFacetName() {
        return this.facetName;
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    public long getFacetCount() {
        return this.facetCount;
    }

    public void setFacetCount(long facetCount) {
        this.facetCount = facetCount;
    }
}