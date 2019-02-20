package com.example.adastraonetask.util;

public class ApiUtils {

    private ApiUtils() {

    }

    public static final String BASE_URL = "https://www.balldontlie.io/api/v1/";

    public static ApiService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);

    }

}
