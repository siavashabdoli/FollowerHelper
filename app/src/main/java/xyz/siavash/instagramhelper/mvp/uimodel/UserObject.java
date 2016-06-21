package xyz.siavash.instagramhelper.mvp.uimodel;

import xyz.siavash.instagramhelper.network.logicmodel.UserItem;

/**
 * Created by siavash on 6/19/16.
 */

public class UserObject {
    public String fullName;
    public String userName;
    public String imageAddress;
    public String followingState;

    public static UserObject createFromUserItem(UserItem userItem,String tempFollowingState){
        UserObject userObject=new UserObject();
        userObject.fullName=userItem.userFullName;
        userObject.userName=userItem.userName;
        userObject.imageAddress=userItem.userImageURL;
        userObject.followingState=tempFollowingState;
        return userObject;
    }
}
