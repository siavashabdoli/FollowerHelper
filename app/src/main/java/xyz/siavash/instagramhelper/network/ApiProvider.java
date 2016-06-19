package xyz.siavash.instagramhelper.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.siavash.instagramhelper.util.ClientUtils;

/**
 * Created by siavash on 5/18/16.
 */

public class ApiProvider {


    private static ApiService mTService;
    private Retrofit mRetrofitClient;
//    private UserPreferenceUtils mAppPreferenceTools;

    private ApiProvider(){
//        this.mAppPreferenceTools = new UserPreferenceUtils();

        //add http interceptor to add headers to each request
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        String originalPath = original.url().url().getPath();
                        Request.Builder requestBuilder = original.newBuilder();
                        requestBuilder.addHeader("Accept", "application/json");
                        requestBuilder.method(original.method(), original.body());

                        //check if it's not authentication return original with json type
                        if(!originalPath.contains("oauth/authorize/"))
                        {
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }

                        requestBuilder.url(ClientUtils.getAuthorizationURL());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);

                    }
                })
                .build();

        Gson gson = new GsonBuilder()
                .create();

        mRetrofitClient = new Retrofit.Builder()
                .baseUrl(ClientUtils.getApiRoot()) // set Base URL , should end with '/'
                .client(httpClient) // add http client
                .addConverterFactory(GsonConverterFactory.create(gson))//add gson converter
                .build();
        mTService = mRetrofitClient.create(ApiService.class);
    }
    /**
     * can get Retrofit Service
     *
     * @return
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
     * @return
     */
    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }
}