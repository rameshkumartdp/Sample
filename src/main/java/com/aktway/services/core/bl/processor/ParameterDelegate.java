package com.aktway.services.core.bl.processor;

import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

import javax.inject.Named;

@SuppressWarnings("unused")
@Named("parameterDelegate")
public class ParameterDelegate extends BaseDelegate {
    private static Logger logger = Logger.getLogger(ParameterDelegate.class.getName());

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        if (serviceRequest.getParameters() != null) {
            serviceRequest.getParameters().keySet().stream().filter(key -> key != null &&
                    serviceRequest.getParameters().get(key) != null).forEach(key -> {
                for (String value : serviceRequest.getParameters().get(key)) {
                    solrQuery.add(key, value);
                }
            });
            logger.info("Pre Processed Query");
        }
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        return serviceResponse;
    }
}