package com.aktway.services.core.bl.processor;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.response.Facet;
import com.aktway.services.core.api.response.ServiceResponse;
import com.aktway.services.core.config.GlobalConstants;
import com.aktway.services.core.response.ResponseGroup;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unused")
@Named("groupDelegate")
public class GroupDelegate extends BaseDelegate {

    private static Logger logger = Logger.getLogger(GroupDelegate.class.getName());

    @Value("${service.groupLimitValue}")
    private int groupLimitValue;

    @Override
    public SolrQuery preProcessQuery(SolrQuery solrQuery, ServiceRequest serviceRequest) {
        String[] groupFields = serviceRequest.getGroupFields();
        if (groupFields != null && groupFields.length > 0) {
            solrQuery.set(GlobalConstants.GROUP, true);
            solrQuery.set(GlobalConstants.GROUP_LIMIT, groupLimitValue);
            for (String groupField : groupFields) {
                solrQuery.add(GlobalConstants.GROUP_FIELD, groupField);
            }
        }
        return solrQuery;
    }

    @Override
    public ServiceResponse postProcessResult(ServiceRequest serviceRequest, QueryResponse queryResponse, ServiceResponse serviceResponse) {
        GroupResponse groupResponse = queryResponse.getGroupResponse();
        if (groupResponse != null) {
            List<ResponseGroup> responseGroups = new ArrayList<>();
            List<GroupCommand> groupCommands = groupResponse.getValues();
            for (GroupCommand groupCommand : groupCommands) {
                ResponseGroup responseGroup = new ResponseGroup();
                responseGroup.setGroupName(groupCommand.getName());
                List<Group> groups = groupCommand.getValues();
                List<Facet> facets = new ArrayList<>();
                for (Group group : groups) {
                    Facet facet = new Facet();
                    facet.setFacetName(group.getGroupValue());
                    facets.add(facet);
                }
                responseGroup.setFacets(facets);
                responseGroups.add(responseGroup);
            }
            serviceResponse.setResponseGroups(responseGroups);
            logger.info("Added Facets");
        }
        return serviceResponse;
    }


}

