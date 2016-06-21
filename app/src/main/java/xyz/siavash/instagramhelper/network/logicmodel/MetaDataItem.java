package xyz.siavash.instagramhelper.network.logicmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by siavash on 6/20/16.
 */
public class MetaDataItem {
    @SerializedName("error_type")
    public String errorType;

    @SerializedName("code")
    public int statusCode;

    @SerializedName("error_message")
    public String message;
}
