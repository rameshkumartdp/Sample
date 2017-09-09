package com.sears.services.commercial.bl.processor;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.ServiceResponse;
import com.sears.services.commercial.config.GlobalConstants;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("afDelegate")
public class AFRequestHandlerDelegate extends BaseDelegate {
    private static Logger logger = Logger.getLogger(AFRequestHandlerDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        solrQuery.setRequestHandler(GlobalConstants.AF_HANDLER);
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        SuggesterResponse suggesterResponse = queryResponse.getSuggesterResponse();
        if (suggesterResponse != null) {
            serviceResponse.setSuggestions(suggesterResponse.getSuggestions());
        }
        return serviceResponse;
    }
}
