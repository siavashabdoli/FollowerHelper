package xyz.siavash.instagramhelper.network.logicmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siavash on 6/18/16.
 */
public class UsersItem {
    @SerializedName("data")
    List<UserItem> userItemList;
}
