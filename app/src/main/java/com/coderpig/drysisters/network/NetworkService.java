package com.coderpig.drysisters.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：
 *
 * @author jay on 2018/1/12 14:53
 */

public enum  NetworkService {
    INSTANCE;

    private static final long HTTP_CONNECT_TIMEOUT = 30L;
    private static final long HTTP_WRITE_TIMEOUT = 30L;
    private static final long HTTP_READ_TIMEOUT = 30L;
    private static OkHttpClient okHttpClient = null;
    private static final String meizi_url = "http://gank.io/api/data/%e7%a6%8f%e5%88%a9/";

    private OkHttpClient buildClient() {
        if(okHttpClient != null) return okHttpClient;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    public String fetchMeizi(int count, int page) {
        String fetchUrl = meizi_url + count + "/" + page;
        Request request = new Request.Builder().url(fetchUrl).build();
        OkHttpClient client = buildClient();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
