package com.ppjun.android.ithome.main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ppjun.android.ithome.R;
import com.ppjun.android.ithome.about.AboutActivity;
import com.ppjun.android.ithome.adapter.ArticleAdapter;
import com.ppjun.android.ithome.base.BaseActivity;
import com.ppjun.android.ithome.util.ThemeHelper;
import com.ppjun.android.ithome.weight.CardPickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ppjun.android.ithome.ithome.Constant.BUNDLE_KEY;
import static com.ppjun.android.ithome.ithome.Constant.CATEGORY_KEY;
import static com.ppjun.android.ithome.ithome.Constant.PREFIX_KEY;
import static com.ppjun.android.ithome.ithome.HomeUrl.ANDROID;
import static com.ppjun.android.ithome.ithome.HomeUrl.ANDROIDURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.ANDROID_PREFIX;
import static com.ppjun.android.ithome.ithome.HomeUrl.DIGI;
import static com.ppjun.android.ithome.ithome.HomeUrl.DIGITAL_PREFIX;
import static com.ppjun.android.ithome.ithome.HomeUrl.DIGIURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.DISCOVERY;
import static com.ppjun.android.ithome.ithome.HomeUrl.DISCOVERYURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.DISCOVERY_PREFIX;
import static com.ppjun.android.ithome.ithome.HomeUrl.IPHONE;
import static com.ppjun.android.ithome.ithome.HomeUrl.IPHONEURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.IPHONE_PREFIX;
import static com.ppjun.android.ithome.ithome.HomeUrl.MAINURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.NEXT;
import static com.ppjun.android.ithome.ithome.HomeUrl.NEXTURL;
import static com.ppjun.android.ithome.ithome.HomeUrl.NEXT_PREFIX;
import static com.ppjun.android.ithome.ithome.HomeUrl.WIN10;
import static com.ppjun.android.ithome.ithome.HomeUrl.WIN10URL;
import static com.ppjun.android.ithome.ithome.HomeUrl.WIN10_PREFIX;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.main_vp)
    ViewPager mViewPager;

    Context mContext;
    ArticleAdapter mArticleAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        setSupportActionBar(mToolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String[] str = {"首页", "android", "ios", "win10", "数码", "智能设备", "科学探索"};
        List<Fragment> list = new ArrayList<>();

        list.add(setArgumentString(new ArticleListFragment(), MAINURL, ANDROID_PREFIX, ANDROID));
        list.add(setArgumentString(new ArticleListFragment(), ANDROIDURL, ANDROID_PREFIX, ANDROID));
        list.add(setArgumentString(new ArticleListFragment(), IPHONEURL, IPHONE_PREFIX, IPHONE));
        list.add(setArgumentString(new ArticleListFragment(), WIN10URL, WIN10_PREFIX, WIN10));
        list.add(setArgumentString(new ArticleListFragment(), DIGIURL, DIGITAL_PREFIX, DIGI));
        list.add(setArgumentString(new ArticleListFragment(), NEXTURL, NEXT_PREFIX, NEXT));
        list.add(setArgumentString(new ArticleListFragment(), DISCOVERYURL, DISCOVERY_PREFIX, DISCOVERY));

        mArticleAdapter = new ArticleAdapter(getSupportFragmentManager(), list, str);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mArticleAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }



    private Fragment setArgumentString(Fragment fragment, String url, String prefix, String category) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY, url);
        bundle.putString(PREFIX_KEY, prefix);
        bundle.putString(CATEGORY_KEY, category);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {


            int currentNightMode = getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK;
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                    ThemeHelper.setLastColor(mContext, ThemeHelper.getTheme(mContext));
                    onConfirm(ThemeHelper.CARD_GRAY);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                    ThemeHelper.setModeNight(mContext, true);

                    break;
                case Configuration.UI_MODE_NIGHT_YES:
                    int color = ThemeHelper.getLastColor(mContext);
                    onConfirm(color);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                    ThemeHelper.setModeNight(mContext, false);
                    break;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            CardPickerDialog dialog = new CardPickerDialog();
            dialog.setClickListener(this);
            dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);


        }
        else if (id == R.id.nav_slideshow) {
           startActivity(new Intent(mContext, AboutActivity.class));
        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
