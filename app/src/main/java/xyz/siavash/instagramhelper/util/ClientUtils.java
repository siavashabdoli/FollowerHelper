package xyz.siavash.instagramhelper.util;

/**
 * Created by siavash on 6/18/16.
 */
public class ClientUtils {
    private static String apiRoot="https://api.instagram.com/v1/";
    private static String authorizationURL;

    public static String getApiRoot() {
        return apiRoot;
    }

    public static String getAuthorizationURL() {
        return authorizationURL;
    }
}
