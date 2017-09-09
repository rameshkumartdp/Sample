package com.sears.services.commercial.bl.processor;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface Delegate {
    SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest);
    ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse);
}
