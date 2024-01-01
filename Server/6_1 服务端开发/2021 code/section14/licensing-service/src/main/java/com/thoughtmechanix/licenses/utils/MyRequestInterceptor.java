package com.thoughtmechanix.licenses.utils;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class MyRequestInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyRequestInterceptor.class);

    @Autowired
    private UserContext userContext;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        Request request = requestTemplate.request();
        String url = request.url();

        requestTemplate.header(UserContext.CORRELATION_ID, userContext.getCorrelationId());
        requestTemplate.header(UserContext.AUTH_TOKEN, userContext.getAuthToken());

        logger.debug("==========MyRequestInterceptor class Correlation id: {}" ,UserContextHolder.getContext().getCorrelationId());

    }
}
