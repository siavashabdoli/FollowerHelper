package xyz.siavash.instagramhelper.network;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;
import xyz.siavash.instagramhelper.network.logicmodel.UsersItem;

/**
 * Created by siavash on 5/25/16.
 */
public interface ApiService {
    /**
     * @param token obtain from Instagram api. Required.
     */
    @GET("users/self/follows")
    Observable<UsersItem> myFollows(@Query("access_token") String token);

    @GET("users/self/followed-by")
    Observable<UsersItem> myFollowers(@Query("access_token") String token);

}
