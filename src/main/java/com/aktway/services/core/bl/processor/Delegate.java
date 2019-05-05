package com.aktway.services.core.bl.processor;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

public interface Delegate {
    SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest);
    ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse);
}
