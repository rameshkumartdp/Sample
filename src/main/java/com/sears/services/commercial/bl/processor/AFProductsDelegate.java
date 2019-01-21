package com.sears.services.commercial.bl.processor;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.api.response.Item;
import com.sears.services.commercial.api.response.ServiceResponse;
import com.sears.services.commercial.util.MiscUtil;
import com.sears.services.commercial.util.SolrDocumentUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Named("afProductsDelegate")
public class AFProductsDelegate extends BaseDelegate {
    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        List<Item> items = new ArrayList<>();
        for (SolrDocument solrDocument : queryResponse.getResults()) {
            String keyword = SolrDocumentUtil.getKeyword(solrDocument);
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
