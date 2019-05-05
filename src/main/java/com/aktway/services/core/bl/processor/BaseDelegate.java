package com.aktway.services.core.bl.processor;

import com.aktway.services.core.util.SolrDocumentUtil;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.*;

public abstract class BaseDelegate implements Delegate {
    protected List<Map<String, String>> buildProducts(SolrDocumentList solrDocuments) {
        List<Map<String, String>> products = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocuments) {
            Collection<String> fieldNames = solrDocument.getFieldNames();
            Map<String, String> product  = new HashMap<>();
            for(String key: fieldNames) {
                product.put(key, SolrDocumentUtil.getFieldValue(solrDocument, key));
            }
            products.add(product);
        }
        return products;
    }
}
