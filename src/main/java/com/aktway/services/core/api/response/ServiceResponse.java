package com.aktway.services.core.api.response;


import com.aktway.services.core.response.FacetGroup;
import com.aktway.services.core.response.ResponseGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.solr.client.solrj.response.Suggestion;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse implements Serializable {

    private List<Map<String, String>> products;
    private Map<String, List<Suggestion>> suggestions;
    private Debug debug;
    private long numFound;
    private List<FacetGroup> facetGroups;
    private List<ResponseGroup> responseGroups;
    private List<Item> items;

    public List<Map<String, String>> getProducts() {
        return products;
    }

    public void setProducts(List<Map<String, String>> products) {
        this.products = products;
    }

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public List<FacetGroup> getFacetGroups() {
        return facetGroups;
    }

    public void setFacetGroups(List<FacetGroup> facetGroups) {
        this.facetGroups = facetGroups;
    }

    public List<ResponseGroup> getResponseGroups() {
        return responseGroups;
    }

    public void setResponseGroups(List<ResponseGroup> responseGroups) {
        this.responseGroups = responseGroups;
    }

    public Map<String, List<Suggestion>> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Map<String, List<Suggestion>> suggestions) {
        this.suggestions = suggestions;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "products=" + products +
                ", suggestions=" + suggestions +
                ", debug=" + debug +
                ", numFound=" + numFound +
                ", facetGroups=" + facetGroups +
                ", responseGroups=" + responseGroups +
                '}';
    }
}
