package xyz.siavash.instagramhelper.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.siavash.instagramhelper.mvp.ui.RelatedUserFragment;


/**
 * Created by siavash on 6/19/16.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {


    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new RelatedUserFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
