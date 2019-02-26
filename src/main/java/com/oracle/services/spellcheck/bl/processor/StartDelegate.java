package com.oracle.services.spellcheck.bl.processor;


import com.oracle.services.spellcheck.api.response.ServiceResponse;
import com.oracle.services.spellcheck.api.request.ServiceRequest;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("startDelegate")
public class StartDelegate  extends BaseDelegate {
    private static Logger logger = Logger.getLogger(StartDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        if (serviceRequest.getStart() != 0) {
            solrQuery.setStart(serviceRequest.getStart());
        }
        logger.info("Set Start Row");
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}
