package com.oracle.services.spellcheck.bl.processor;


import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("numFoundDelegate")
public class NumFoundDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(NumFoundDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        if(queryResponse != null && queryResponse.getResults() != null) {
            serviceResponse.setNumFound(queryResponse.getResults().getNumFound());
            logger.info("Set Num-Found");
        }
        return serviceResponse;
    }
}