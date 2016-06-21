package xyz.siavash.instagramhelper.util;

import xyz.siavash.instagramhelper.BuildConfig;

/**
 * Created by siavash on 6/18/16.
 */
public class ClientUtils {
    private static String apiRoot="https://api.instagram.com/v1/";
    private static String authorizationURLPart1=
            "https://api.instagram.com/oauth/authorize/?client_id=";
    private static String authorizationURLPart2 = "&redirect_uri=https://siavash.xyz&response_type=token";
    private static String clientID=BuildConfig.CLIENT_ID;
    private static String clientSecret=BuildConfig.CLIENT_SECRET;

    public static String getApiRoot() {
        return apiRoot;
    }

    public static String getAuthorizationURL() {
        return authorizationURLPart1+clientID+authorizationURLPart2;
    }
}
