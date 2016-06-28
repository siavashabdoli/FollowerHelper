package xyz.siavash.instagramhelper.util;

import java.util.Comparator;

import xyz.siavash.instagramhelper.network.logicmodel.UserItem;

/**
 * Created by siavash on 6/28/16.
 */

public class UserSortComprator implements Comparator<UserItem> {
    @Override
    public int compare(UserItem lhs, UserItem rhs) {
        return lhs.userName.compareTo(rhs.userName);
    }
}
