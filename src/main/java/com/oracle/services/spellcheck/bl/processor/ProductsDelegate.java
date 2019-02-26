package com.oracle.services.spellcheck.bl.processor;


import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;
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
