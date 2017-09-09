package com.sears.services.commercial.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.List;

public class GlobalConstants {

    public static final String REQUEST = "requests";
    public static final String ID = "id";
    public static final String SEARCH_HANDLER = "search";
    public static final String AF_HANDLER = "/suggest";
    public static final String ASC = "asc";
    public static List<String> KNOWN_PARAMETERS;

    public static String APPLICATION_NAME;
    static {
        KNOWN_PARAMETERS = Arrays.asList(com.sears.search.config.GlobalConstants.Q,
                com.sears.search.config.GlobalConstants.FQ,
                com.sears.search.config.GlobalConstants.FACET_FIELDS,
                com.sears.search.config.GlobalConstants.FACET_SORT,
                com.sears.search.config.GlobalConstants.GROUP_FIELDS,
                com.sears.search.config.GlobalConstants.ROWS,
                com.sears.search.config.GlobalConstants.COUNT,
                com.sears.search.config.GlobalConstants.SORT,
                com.sears.search.config.GlobalConstants.SORT_ORDER,
                com.sears.search.config.GlobalConstants.DEBUG,
                com.sears.search.config.GlobalConstants.START);
    }

    @Value("${spring.application.name}")
    public void setApplicationName(String applicationName) {
        APPLICATION_NAME = applicationName;
    }
}
