package xyz.siavash.instagramhelper.network;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.siavash.instagramhelper.util.ClientUtils;

/**
 * Created by siavash on 5/18/16.
 */

public class ApiProvider {


    private static ApiService mTService;
    private Retrofit mRetrofitClient;
    private ApiProvider(){

        OkHttpClient httpClient = new OkHttpClient.Builder()
                                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Log.d("path:","here:"+original.url().url().getPath());
                        Request.Builder requestBuilder = original.newBuilder();

                        requestBuilder.addHeader("Accept", "application/json");

                        requestBuilder.method(original.method(), original.body());
                        Request request = requestBuilder.build();return chain.proceed(request);

                    }
                })
                .build();


        Gson gson = new GsonBuilder()
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientUtils.getApiRoot()) // set Base URL , should end with '/'
                .client(httpClient) // add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mTService = mRetrofitClient.create(ApiService.class);
    }
    /**
     * can get Retrofit Service
     *
     * @return retroft api service
     */
    public static ApiService getTService() {
        if(mTService!=null)
        return mTService;

        new ApiProvider();

        return mTService;
    }

    /**
     * get Retrofit client
     * used in ErrorUtil class
     *
     * @return retrofit client
     */
    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }
}