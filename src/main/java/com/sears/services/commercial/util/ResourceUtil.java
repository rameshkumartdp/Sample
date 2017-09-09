package com.sears.services.commercial.util;

import com.sears.search.service.api.response.From;
import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.config.GlobalConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;

public class ResourceUtil {
    private static Logger logger = Logger.getLogger(ResourceUtil.class.getName());

    public static String getFirstIfPresent(String[] inputList) {
        if (inputList == null || inputList.length == 0) {
            return null;
        }
        return inputList[0];
    }


//    public static ServiceRequest buildServiceRequest(MultivaluedMap<String, String> queryParams) {
    public static ServiceRequest buildServiceRequest(Map<String, String[]> queryParams) {
        ServiceRequest serviceRequest = new ServiceRequest();
        Map<String, List<String>> parameters = new HashMap<>();
        serviceRequest.setQ(getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.Q)));
        serviceRequest.setFq(getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.FQ)));
        String rows = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.ROWS));
        String facetFieldParam = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.FACET_FIELDS));
        String facetSortParam = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.FACET_SORT));
        String groupFieldParam = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.GROUP_FIELDS));

        String sort = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.SORT));
        if (StringUtils.isNoneEmpty(sort)) {
            serviceRequest.setSort(sort);
        }
        String sortOrder = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.SORT_ORDER));
        if (StringUtils.isNoneEmpty(sortOrder)) {
            serviceRequest.setSortOrder(sortOrder);
        }
        if (StringUtils.isNumeric(rows)) {
            serviceRequest.setRows(Integer.parseInt(rows));
        }
        String start = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.START));
        if (StringUtils.isNumeric(start)) {
            serviceRequest.setStart(Integer.parseInt(start));
        }

        if (StringUtils.isNotEmpty(facetFieldParam)) {
            serviceRequest.setFacetFields(facetFieldParam.split(com.sears.search.config.GlobalConstants.COMMA));
        }

        if (StringUtils.isNotEmpty(groupFieldParam)) {
            serviceRequest.setGroupFields(groupFieldParam.split(com.sears.search.config.GlobalConstants.COMMA));
        }

        if (StringUtils.isNotEmpty(facetSortParam)) {
            serviceRequest.setFacetSort(facetSortParam);
        }
        String fromParamValue = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.FROM));
        if (StringUtils.isNotEmpty(fromParamValue)) {
            serviceRequest.setFrom(From.getFrom(fromParamValue));
        }

        String debug = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.DEBUG));
        Set<String> parameterNames = queryParams.keySet();
        for (String paramName : parameterNames) {
            if (!GlobalConstants.KNOWN_PARAMETERS.contains(paramName)) {
                parameters.put(paramName, Arrays.asList(queryParams.get(paramName)));
            }
        }
        serviceRequest.setParameters(parameters);
        if (StringUtils.isNotEmpty(debug) && debug.equals(com.sears.search.config.GlobalConstants.TRUE)) {
            serviceRequest.setDebug(true);
            logger.info(serviceRequest);
        }
        return serviceRequest;
    }

    //    public static ServiceRequest buildServiceRequest(MultivaluedMap<String, String> queryParams) {
    public static ServiceRequest buildAFServiceRequest(Map<String, String[]> queryParams) {
        ServiceRequest serviceRequest = new ServiceRequest();
        Map<String, List<String>> parameters = new HashMap<>();
        serviceRequest.setQ(getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.Q)));
        String debug = getFirstIfPresent(queryParams.get(com.sears.search.config.GlobalConstants.DEBUG));
        Set<String> parameterNames = queryParams.keySet();
        for (String paramName : parameterNames) {
            if (!GlobalConstants.KNOWN_PARAMETERS.contains(paramName)) {
                parameters.put(paramName, Arrays.asList(queryParams.get(paramName)));
            }
        }
        serviceRequest.setParameters(parameters);
        if (StringUtils.isNotEmpty(debug) && debug.equals(com.sears.search.config.GlobalConstants.TRUE)) {
            serviceRequest.setDebug(true);
            logger.info(serviceRequest);
        }
        return serviceRequest;
    }
}
