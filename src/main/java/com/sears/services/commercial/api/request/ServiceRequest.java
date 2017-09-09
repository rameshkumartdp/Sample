package com.sears.services.commercial.api.request;

import com.sears.search.service.api.response.From;
import com.sears.services.commercial.config.GlobalConstants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServiceRequest implements Serializable {
    private String q;
    private String sort = GlobalConstants.ID;
    private String sortOrder = GlobalConstants.ASC;
    private int rows;
    private int start;
    private String fq;
    private boolean debug;
    private int round;
    private String facetSort = com.sears.search.config.GlobalConstants.COUNT;
    private String[] facetFields;
    private String[] groupFields;
    private From from = From.DEFAULT;
    private Map<String, List<String>> parameters ;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String[] getFacetFields() {
        return facetFields;
    }

    public void setFacetFields(String[] facetFields) {
        this.facetFields = facetFields;
    }

    public String[] getGroupFields() {
        return groupFields;
    }

    public void setGroupFields(String[] groupFields) {
        this.groupFields = groupFields;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getFacetSort() {
        return facetSort;
    }

    public void setFacetSort(String facetSort) {
        this.facetSort = facetSort;
    }

    public Map<String, List<String>> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, List<String>> parameters) {
        this.parameters = parameters;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "q='" + q + '\'' +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", rows=" + rows +
                ", start=" + start +
                ", fq='" + fq + '\'' +
                ", debug=" + debug +
                ", round=" + round +
                ", facetSort='" + facetSort + '\'' +
                ", facetFields=" + Arrays.toString(facetFields) +
                ", groupFields=" + Arrays.toString(groupFields) +
                ", from=" + from +
                ", parameters=" + parameters +
                '}';
    }

    public String toCacheKey() {
        System.out.println("INSIDEEEE cacheKEYYYYYYYYYYYYYYYYYY");
        return "ServiceRequest{" +
                "q='" + q + '\'' +
                ", sort='" + sort + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", rows=" + rows +
                ", start=" + start +
                ", fq='" + fq + '\'' +
                ", debug=" + debug +
                ", round=" + round +
                ", facetSort='" + facetSort + '\'' +
                ", facetFields=" + Arrays.toString(facetFields) +
                ", groupFields=" + Arrays.toString(groupFields) +
                ", parameters=" + parameters +
                '}';
    }

    public String getCacheKey() {
        return String.valueOf(toCacheKey().hashCode());
    }
}
