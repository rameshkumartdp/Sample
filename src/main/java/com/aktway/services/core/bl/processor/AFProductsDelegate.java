package com.aktway.services.core.bl.processor;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.api.response.Item;
import com.aktway.services.core.api.response.ServiceResponse;
import com.aktway.services.core.util.SolrDocumentUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
@Named("afProductsDelegate")
public class AFProductsDelegate extends BaseDelegate {
    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        Set<Item> items = new LinkedHashSet<>();
        for (SolrDocument solrDocument : queryResponse.getResults()) {
            String keyword = SolrDocumentUtil.getKeyword(solrDocument).toLowerCase();
            keyword = keyword.substring(1, keyword.length()-1);
            Item item = new Item();
            item.setK(keyword);
            items.add(item);
        }
        if (items.size() > 0) {
            serviceResponse.setItems(items);
        }
        return serviceResponse;
    }


}
