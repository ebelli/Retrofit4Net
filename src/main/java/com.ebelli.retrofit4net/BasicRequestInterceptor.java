package com.ebelli.retrofit4net;

import retrofit.RequestInterceptor;

/**
 * Created by ebelli on 06/07/15.
 */
public class BasicRequestInterceptor implements RequestInterceptor {

    private final String appId;

    public BasicRequestInterceptor(String appId) {
        this.appId = appId;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        requestFacade.addHeader("User-Agent", appId);
    }
}