package com.ppjun.android.ithome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Package :com.ppjun.android.ithome.adapter
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 11:53.
 */

public class ArticleAdapter extends FragmentPagerAdapter {
    List<Fragment> mList;
    String[] str;

    public ArticleAdapter(FragmentManager fm, List<Fragment> mList, String[] str) {
        super(fm);
        this.mList = mList;
        this.str = str;

    }

    public ArticleAdapter setList(List<Fragment> list) {
        mList = list;
        return this;
    }

    public ArticleAdapter setStr(String[] str) {
        this.str = str;
        return this;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];

    }
}
