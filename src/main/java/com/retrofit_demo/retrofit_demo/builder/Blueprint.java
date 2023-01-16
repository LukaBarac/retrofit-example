package com.retrofit_demo.retrofit_demo.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Blueprint {

    private final static String BASE_URL = "http://localhost:8080/";  // stavio final jer je intellij sugest.
    private final static Gson GSON = new GsonBuilder() //capslock zbog 'konvencije'
            .setLenient()
            .create();

   /*     OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                String token;
                @Override
                public Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                }
        }).build();*/

   /* static OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(chain -> {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                Credentials.basic("peraperic@gmail.com", "baguvix"));

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }).build();*/
    private final static Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GSON))
            /*.client(okHttpClient)*/
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return RETROFIT.create(serviceClass);
    }




}
