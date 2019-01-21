package com.sears.services.commercial.util;


import com.sears.services.commercial.config.GlobalConstants;
import org.apache.solr.common.SolrDocument;

import java.util.List;
import java.util.stream.Collectors;

public class SolrDocumentUtil {

    public static String getFieldValue(SolrDocument solrDocument, String field) {
        Object object = solrDocument.getFieldValue(field);
        if(object == null) {
            return null;
        }
        return String.valueOf(object);
    }

    public static List<String> getFieldValues(SolrDocument solrDocument, String field) {
        if(solrDocument == null) {
            return null;
        }
        Object o = solrDocument.getFieldValues(field);
        if(o == null) {
            return null;
        }
        return solrDocument.getFieldValues(field).stream().filter(object -> object != null).map(String::valueOf).collect(Collectors.toList());
    }

    public static String getId(SolrDocument solrDocument) {
        return getFieldValue(solrDocument, GlobalConstants.ID);
    }

    public static String getKeyword(SolrDocument solrDocument) {
        return getFieldValue(solrDocument, "name");
    }
}
