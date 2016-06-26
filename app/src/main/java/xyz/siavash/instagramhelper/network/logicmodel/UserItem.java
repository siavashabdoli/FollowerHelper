package xyz.siavash.instagramhelper.network.logicmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by siavash on 6/18/16.
 */
public class UserItem {
    @SerializedName("username")
    public String userName;

    @SerializedName("profile_picture")
    public String userImageURL;

    @SerializedName("full_name")
    public String userFullName;

    @SerializedName("id")
    public int userID;
}
