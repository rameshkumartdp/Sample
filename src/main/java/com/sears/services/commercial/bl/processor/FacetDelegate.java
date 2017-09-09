package com.sears.services.commercial.bl.processor;


import com.sears.search.service.api.response.Facet;
import com.sears.search.service.api.response.FacetGroup;
import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
@Named("facetDelegate")
public class FacetDelegate implements Delegate {
    private static Logger logger = Logger.getLogger(FacetDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        String[] facetFields = serviceRequest.getFacetFields();
        if (facetFields != null && facetFields.length > 0) {
            solrQuery.setFacet(true);
            solrQuery.setFacetMinCount(1);
            solrQuery.addFacetField(facetFields);
            solrQuery.setFacetSort(serviceRequest.getFacetSort());
        }
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        List<FacetField> facetFieldList = queryResponse.getFacetFields();
        if (facetFieldList != null) {
            List<FacetGroup> facetGroups = new ArrayList<>();
            for (FacetField facetField : facetFieldList) {
                FacetGroup facetGroup = new FacetGroup();
                facetGroup.setGroupName(facetField.getName());
                List<Facet> facets = new ArrayList<>();
                for (FacetField.Count count : facetField.getValues()) {
                    Facet facet = new Facet();
                    facet.setFacetName(count.getName());
                    facet.setFacetCount(count.getCount());
                    facets.add(facet);
                }
                facetGroup.setFacets(facets);
                facetGroups.add(facetGroup);
            }
            serviceResponse.setFacetGroups(facetGroups);
            logger.info("Added Facet Groups");
        }


        return serviceResponse;
    }
}

