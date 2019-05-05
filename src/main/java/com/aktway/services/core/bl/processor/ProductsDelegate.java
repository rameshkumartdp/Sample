package com.aktway.services.core.bl.processor;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.ServiceResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import javax.inject.Named;
import java.util.*;
@SuppressWarnings("unused")
@Named("productsDelegate")
public class ProductsDelegate extends BaseDelegate {
    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        SolrDocumentList solrDocs = queryResponse.getResults();
        if(solrDocs == null) {
            return serviceResponse;
        }
        List<Map<String, String>> products = buildProducts(solrDocs);
        if (!products.isEmpty()) {
            serviceResponse.setProducts(products);
        }
        return serviceResponse;
    }


}
