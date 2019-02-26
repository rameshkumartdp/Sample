package com.oracle.services.spellcheck.resource;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.oracle.services.spellcheck.api.request.ServiceRequest;
import com.oracle.services.spellcheck.api.response.ServiceResponse;
import com.oracle.services.spellcheck.bl.QueryService;
import com.oracle.services.spellcheck.util.ResourceUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Produces(MediaType.APPLICATION_JSON)
@RestController
public class ServiceResource {
    @Inject
    private QueryService queryService;
    private Logger logger = LoggerFactory.getLogger(ServiceResource.class.getName());

    public ServiceResource() {
        //Empty
    }


    @Timed
    @ExceptionMetered
    @RequestMapping("/search")
    @ApiOperation(
            value = "Get commercial search results ",
            notes = "Pass q and other parameters to get relevant suggestions back ",
            response = ServiceResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "q", value = "Search keyword parameter ", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "fq", value = "Provide solr filter capabilities  ", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facet.fields", value = "comma seperated list of fields that we need to return facet on ", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "facet.sort", value = "To sort facet response", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "rows", value = "To limit the number of rows returned", required = false, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "start", value = "To return documents starting from that number . To help with pagination", required = false, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "from", value = "To bypass cache by sending value from=index", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "To sort the returned response", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort.order", value = "To modify the sort order as asc and desc", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "debug", value = "To enable debugging ", required = false, dataType = "string", paramType = "query"),
    })
    public ServiceResponse search(WebRequest webRequest) {
        Map<String, String[]> queryParams = webRequest.getParameterMap();
        ServiceRequest serviceRequest = ResourceUtil.buildServiceRequest(queryParams);
        try {
            return queryService.query(serviceRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new WebApplicationException(ex);
        }

    }

    @Timed
    @ExceptionMetered
    @RequestMapping("/suggest")
    @ApiOperation(
            value = "Get commercial search results ",
            notes = "Pass q to get the suggestions back ",
            response = ServiceResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "q", value = "Search keyword parameter ", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "debug", value = "To enable debugging ", required = false, dataType = "string", paramType = "query"),
    })
    public ServiceResponse suggest(WebRequest webRequest) {
        Map<String, String[]> queryParams = webRequest.getParameterMap();
        ServiceRequest serviceRequest = ResourceUtil.buildAFServiceRequest(queryParams);
        try {
            return queryService.queryAF(serviceRequest);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new WebApplicationException(ex);
        }

    }


}
