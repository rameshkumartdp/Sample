package com.oracle.services.spellcheck.util;

import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.config.GlobalConstants;
import com.oracle.services.spellcheck.response.From;
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
        serviceRequest.setQ(getFirstIfPresent(queryParams.get(GlobalConstants.Q)));
        serviceRequest.setFq(getFirstIfPresent(queryParams.get(GlobalConstants.FQ)));
        String rows = getFirstIfPresent(queryParams.get(GlobalConstants.ROWS));
        String facetFieldParam = getFirstIfPresent(queryParams.get(GlobalConstants.FACET_FIELDS));
        String facetSortParam = getFirstIfPresent(queryParams.get(GlobalConstants.FACET_SORT));
        String groupFieldParam = getFirstIfPresent(queryParams.get(GlobalConstants.GROUP_FIELDS));

        String sort = getFirstIfPresent(queryParams.get(GlobalConstants.SORT));
        if (StringUtils.isNoneEmpty(sort)) {
            serviceRequest.setSort(sort);
        }
        String sortOrder = getFirstIfPresent(queryParams.get(GlobalConstants.SORT_ORDER));
        if (StringUtils.isNoneEmpty(sortOrder)) {
            serviceRequest.setSortOrder(sortOrder);
        }
        if (StringUtils.isNumeric(rows)) {
            serviceRequest.setRows(Integer.parseInt(rows));
        }
        String start = getFirstIfPresent(queryParams.get(GlobalConstants.START));
        if (StringUtils.isNumeric(start)) {
            serviceRequest.setStart(Integer.parseInt(start));
        }

        if (StringUtils.isNotEmpty(facetFieldParam)) {
            serviceRequest.setFacetFields(facetFieldParam.split(GlobalConstants.COMMA));
        }

        if (StringUtils.isNotEmpty(groupFieldParam)) {
            serviceRequest.setGroupFields(groupFieldParam.split(GlobalConstants.COMMA));
        }

        if (StringUtils.isNotEmpty(facetSortParam)) {
            serviceRequest.setFacetSort(facetSortParam);
        }
        String fromParamValue = getFirstIfPresent(queryParams.get(GlobalConstants.FROM));
        if (StringUtils.isNotEmpty(fromParamValue)) {
            serviceRequest.setFrom(From.INDEX);
        }

        String debug = getFirstIfPresent(queryParams.get(GlobalConstants.DEBUG));
        Set<String> parameterNames = queryParams.keySet();
        for (String paramName : parameterNames) {
            if (!GlobalConstants.KNOWN_PARAMETERS.contains(paramName)) {
                parameters.put(paramName, Arrays.asList(queryParams.get(paramName)));
            }
        }
        serviceRequest.setParameters(parameters);
        if (StringUtils.isNotEmpty(debug) && debug.equals(GlobalConstants.TRUE)) {
            serviceRequest.setDebug(true);
            logger.info(serviceRequest);
        }
        return serviceRequest;
    }

    //    public static ServiceRequest buildServiceRequest(MultivaluedMap<String, String> queryParams) {
    public static ServiceRequest buildAFServiceRequest(Map<String, String[]> queryParams) {
        ServiceRequest serviceRequest = new ServiceRequest();
        Map<String, List<String>> parameters = new HashMap<>();
        serviceRequest.setQ(getFirstIfPresent(queryParams.get(GlobalConstants.Q)));
        String debug = getFirstIfPresent(queryParams.get(GlobalConstants.DEBUG));
        Set<String> parameterNames = queryParams.keySet();
        for (String paramName : parameterNames) {
            if (!GlobalConstants.KNOWN_PARAMETERS.contains(paramName)) {
                parameters.put(paramName, Arrays.asList(queryParams.get(paramName)));
            }
        }
        serviceRequest.setParameters(parameters);
        if (StringUtils.isNotEmpty(debug) && debug.equals(GlobalConstants.TRUE)) {
            serviceRequest.setDebug(true);
            logger.info(serviceRequest);
        }
        return serviceRequest;
    }
}
