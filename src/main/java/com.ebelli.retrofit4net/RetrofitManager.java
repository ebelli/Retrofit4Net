package com.ebelli.retrofit4net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Creates and return a singleton RestAdapter to be used throut all the application
 *
 * To set the default endpoint you need to call the setEndpoint(string) method.
 * To add a RequestInterceptor that add information in every call, call the setRequestInterceptor method
 * To set a Log, call the setLog method; if setLog is not called, nothing will be logged
 *
 * @author EnzoBelli on 26/06/15.
 */
public class RetrofitManager {

    public static final int TIMEOUT = 40;
    private static String ENDPOINT = null;
    private static ApiManager REST_CLIENT;

    private static RestAdapter.Log LOG;
    private static RequestInterceptor REQUEST_INTERCEPTOR;
    private static String exceptionMessage= "Endpoint is null; To set the default endpoint you need to call the setEndpoint(string) method.";

    public static ApiManager get() throws EndpointNotFoundException {
        if (REST_CLIENT == null){
            setupRestClient();
        }
        return REST_CLIENT;
    }


    private static void setupRestClient() throws EndpointNotFoundException {
        if (ENDPOINT == null) {
            throw new EndpointNotFoundException(exceptionMessage);
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDateUtils.JsonDateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDateUtils.JsonDateDeserializer());
        Gson gson =gsonBuilder.create();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.SECONDS); // connection timeout
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if (REQUEST_INTERCEPTOR != null) {
            builder.setRequestInterceptor(REQUEST_INTERCEPTOR);
        }
        if (LOG != null) {
            builder.setLog(LOG);
        }

        RestAdapter restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(ApiManager.class);
    }

    public static String getEndpoint() {
        return ENDPOINT;
    }

    public static void setEndpoint(String ENDPOINT) {
        RetrofitManager.ENDPOINT = ENDPOINT;
    }

    public static RequestInterceptor getRequestInterceptor() {
        return REQUEST_INTERCEPTOR;
    }

    public static void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        REQUEST_INTERCEPTOR = requestInterceptor;
    }

    public static RestAdapter.Log getLog() {
        return LOG;
    }

    public static void setLog(RestAdapter.Log Log) {
        RetrofitManager.LOG = Log;
    }


}

