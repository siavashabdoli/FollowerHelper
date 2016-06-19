package xyz.siavash.instagramhelper.network.logicmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by siavash on 6/18/16.
 */
public class UserItem {
    @SerializedName("username")
    String userName;

    @SerializedName("profile_pictrue")
    String userImageURL;

    @SerializedName("full_name")
    String userFullName;

    @SerializedName("id")
    int userID;
}
