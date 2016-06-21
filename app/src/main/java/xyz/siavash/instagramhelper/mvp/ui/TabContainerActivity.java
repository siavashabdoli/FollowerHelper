package xyz.siavash.instagramhelper.mvp.ui;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.siavash.instagramhelper.R;
import xyz.siavash.instagramhelper.mvp.ui.adapter.MainPagerAdapter;



public class TabContainerActivity extends AppCompatActivity {
    @Bind(R.id.pager)
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("siavash","activity");
        setContentView(R.layout.tab_container_activity);
        ButterKnife.bind(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainPagerAdapter pagerAdapter=new MainPagerAdapter(fragmentManager);
        viewpager.setAdapter(pagerAdapter);
        Log.d("siavash","activity2");
    }
}
