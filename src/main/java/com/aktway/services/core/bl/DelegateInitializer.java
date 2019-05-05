package com.aktway.services.core.bl;


import com.aktway.services.core.api.request.ServiceRequest;
import com.aktway.services.core.bl.processor.DebugDelegate;
import com.aktway.services.core.bl.processor.Delegate;
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
    @Named("afQueryTermDelegate")
    private Delegate afQueryTermDelegate;
    @Inject
    @Named("fieldsDelegate")
    private Delegate fieldsDelegate;
    @Inject
    @Named("requestHandlerDelegate")
    private Delegate requestHandlerDelegate;
    @Inject
    @Named("afRequestHandlerDelegate")
    private Delegate afRequestHandlerDelegate;
    @Inject
    @Named("productsDelegate")
    private Delegate productsDelegate;
    @Inject
    @Named("afProductsDelegate")
    private Delegate afProductsDelegate;
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
        mainDelegateList.add(afQueryTermDelegate);
        mainDelegateList.add(afRequestHandlerDelegate);
        mainDelegateList.add(fieldsDelegate);
        mainDelegateList.add(afProductsDelegate);
        Map<String, List<Delegate>> delegateMapList = new HashMap<>();

        if(serviceRequest.isDebug()) {
            mainDelegateList.add(new DebugDelegate());
            logger.info("Delegate Map List is " + mainDelegateList);
        }
        delegateMapList.put("", mainDelegateList);
        return delegateMapList;

    }

}
