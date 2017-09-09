package com.sears.services.commercial.bl;


import com.sears.services.commercial.api.request.ServiceRequest;
import com.sears.services.commercial.bl.processor.*;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class DelegateInitializer {
    private static Logger logger = Logger.getLogger(DelegateInitializer.class.getName());
    @Inject
    @Named("queryTermDelegate")
    private Delegate queryTermDelegate;
    @Inject
    @Named("requestHandlerDelegate")
    private Delegate requestHandlerDelegate;
    @Inject
    @Named("productsDelegate")
    private Delegate productsDelegate;
    @Inject
    @Named("timeAllowedDelegate")
    private Delegate timeAllowedDelegate;
    @Inject
    @Named("filterDelegate")
    private Delegate filterDelegate;
    @Inject
    @Named("sortDelegate")
    private Delegate sortDelegate;
    @Inject
    @Named("rowsDelegate")
    private Delegate rowsDelegate;
    @Inject
    @Named("startDelegate")
    private Delegate startDelegate;
    @Inject
    @Named("parameterDelegate")
    private Delegate parameterDelegate;
    @Inject
    @Named("numFoundDelegate")
    private Delegate numFoundDelegate;
    @Inject
    @Named("facetDelegate")
    private Delegate facetDelegate;
    @Inject
    @Named("groupDelegate")
    private Delegate groupDelegate;
    @Inject
    @Named("afDelegate")
    private Delegate afDelegate;


    public Map<String, List<Delegate>> buildDelegateMapList(ServiceRequest serviceRequest) {
        List<Delegate> mainDelegateList = new ArrayList<>();
        mainDelegateList.add(queryTermDelegate);
        mainDelegateList.add(requestHandlerDelegate);
        mainDelegateList.add(timeAllowedDelegate);
        mainDelegateList.add(productsDelegate);
        mainDelegateList.add(filterDelegate);
        mainDelegateList.add(sortDelegate);
        mainDelegateList.add(rowsDelegate);
        mainDelegateList.add(startDelegate);
        mainDelegateList.add(numFoundDelegate);
        mainDelegateList.add(facetDelegate);
        mainDelegateList.add(groupDelegate);
        mainDelegateList.add(parameterDelegate);
        Map<String, List<Delegate>> delegateMapList = new HashMap<>();

        if(serviceRequest.isDebug()) {
            mainDelegateList.add(new DebugDelegate());
            logger.info("Delegate Map List is " + mainDelegateList);
        }
        delegateMapList.put("", mainDelegateList);
        return delegateMapList;

    }


    public Map<String, List<Delegate>> buildAFDelegateMapList(ServiceRequest serviceRequest) {
        List<Delegate> mainDelegateList = new ArrayList<>();
        mainDelegateList.add(queryTermDelegate);
        mainDelegateList.add(afDelegate);
        Map<String, List<Delegate>> delegateMapList = new HashMap<>();

        if(serviceRequest.isDebug()) {
            mainDelegateList.add(new DebugDelegate());
            logger.info("Delegate Map List is " + mainDelegateList);
        }
        delegateMapList.put("", mainDelegateList);
        return delegateMapList;

    }

}
