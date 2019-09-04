package com.aktway.services.core.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
        import java.util.List;

public class GlobalConstants {

    public static final String REQUEST = "requests";
    public static final String SEARCH_HANDLER = "search";
    public static final String AF_HANDLER = "select";
    public static final String ASC = "asc";
    public static List<String> KNOWN_PARAMETERS;
    public static final String DEFAULT_ENV = "BETA";
    public static final String SERVICE_ENVIRONMENT = "SERVICE_ENVIRONMENT";
    public static final String BASE_CONFIG_FILE_NAME_SUFFIX = ".properties";
    public static final String Q = "q";
    public static final String TRUE = "true";
    public static final String FROM = "from";
    public static final String DEBUG = "debug";
    public static final String SPACE = " ";
    public static final String SEMI_COLON = ";";
    public static final String COLON = ":";
    public static final String ID = "id";
    public static final String OR = "OR";
    public static final String COMMA = ",";
    public static final String UTF8 = "UTF-8";
    public static final String UNDERSCORE = "_";
    public static final String LEFT_PARENTHESIS = "(";
    public static final String RIGHT_PARENTHESIS = ")";
    public static final String LOCAL_ATTRIBUTE = "localStatus";
    public static final String GLOBAL_ATTRIBUTE = "globalStatus";
    public static final String LOCAL_PARAM = "local";
    public static final String GLOBAL_PARAM = "global";
    public static final String DOWN = "DOWN";
    public static final String UP = "UP";
    public static String ONE = "1";
    public static String ZERO = "0";
    public static final String AMPERSAND = "&";
    public static final String QUESTION_MARK = "?";
    public static final String DOT = ".";
    public static final String STAR = "*";
    public static final String EQUAL = "=";
    public static final String TILDE = "~";
    public static final String CARROT = "^";
    public static final String TOTAL_TIME = "total time";
    public static final String CALLBACK = "callback";
    public static final String SERVICE = "service";
    public static final String CALLBACK_COMMENT = "/**/";
    public static final String APPLICATION_JSON = "application/json";
    public static final String TEXT_JAVA_SCRIPT = "text/javascript;charset=UTF-8";
    public static final String CONTENT_TYPE_TEXT_PLAIN_CHARSET_UTF_8 = "contentType=text/plain;charset=utf-8";
    public static final String HTTP = "http";
    public static final String TEXT_HTML = "text/http";
    public static final String RIGHT_PARENTHESIS_SEMI_COLON = ");";
    public static final String NEW_LINE = "\n";
    public static String INDEX = "index";
    public static String CACHE = "cache";
    public static String DEFAULT = "default";
    public static final String GROUP = "group";
    public static final String GROUP_LIMIT = "group.limit";
    public static final String GROUP_FIELD = "group.field";
    public static final String FQ = "fq";
    public static final String FACET_FIELDS = "facet.fields";
    public static final String FACET_SORT = "facet.sort";
    public static final String GROUP_FIELDS = "group.fields";
    public static final String ROWS = "rows";
    public static final String COUNT = "count";
    public static final String START = "start";
    public static final String SORT = "sort";
    public static final String SORT_ORDER = "sort.order";
    public static final String FALSE = "false";
    public static final String SLASH = "/";
    public static final String ENABLE_SWAGGER = "enableSwagger";
    public static final String ENABLE_DISCOVERY = "enableDiscovery";

    public static String APPLICATION_NAME;
    static {
        KNOWN_PARAMETERS = Arrays.asList(Q,
                FQ,
                FACET_FIELDS,
                FACET_SORT,
                GROUP_FIELDS,
                ROWS,
                COUNT,
                SORT,
                SORT_ORDER,
                DEBUG,
                START);
    }

    @Value("${spring.application.name}")
    public void setApplicationName(String applicationName) {
        APPLICATION_NAME = applicationName;
    }
}
