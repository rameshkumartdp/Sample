package com.aktway.services.core.bl.processor;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import com.aktway.services.core.config.GlobalConstants;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SuggesterResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("afRequestHandlerDelegate")
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
