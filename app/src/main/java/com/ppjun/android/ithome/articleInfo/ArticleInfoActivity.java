package com.ppjun.android.ithome.articleInfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppjun.android.ithome.R;
import com.ppjun.android.ithome.base.BaseActivity;
import com.ppjun.android.ithome.bean.ArticleInfo;
import com.ppjun.android.ithome.ithome.Constant;
import com.ppjun.android.ithome.util.GlideUtil;
import com.ppjun.android.ithome.weight.CardPickerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Package :com.ppjun.android.ithome.ArticleInfo
 * Description :
 * Author :Rc3
 * Created at :2017/3/29 14:25.
 */

public class ArticleInfoActivity extends BaseActivity implements ArticleInfoContract.View,
        CardPickerDialog.ClickListener {
    ArticleInfoContract.Presenter mPresenter;
    @BindView(R.id.article_ll)
    LinearLayout mLinearLayout;
    Context mContext;
    @BindView(R.id.artile_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_info);
        mContext = this;
        ButterKnife.bind(this);
        new ArticlePresenter(this);
        mPresenter.requestData(getIntent().getStringExtra(Constant.ONCLICK_KEY));

    }

    @Override
    public void setPresenter(ArticleInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setData(ArticleInfo info) {
        mToolbar.setTitle(info.getTitle());
        mToolbar.setSubtitle(info.getCreateTime());
        setSupportActionBar(mToolbar);
        List<ArticleInfo.ArticleDetail> list = new ArrayList<>();
        list.addAll(info.getList());
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0, 10, 0, 10);

        for (int i = 0; i < list.size(); i++) {
            ArticleInfo.ArticleDetail detail = list.get(i);
            TextView text = new TextView(mContext);
            text.setText(detail.getContent());
            text.setLayoutParams(textParams);

            mLinearLayout.addView(text);
            if (!TextUtils.isEmpty(detail.getImageUrlSrc())) {
                ImageView imageview1 = new ImageView(mContext);

                GlideUtil.displayImage(detail.getImageUrlSrc(), imageview1);
                mLinearLayout.addView(imageview1);
            }
            if (!TextUtils.isEmpty(detail.getImageUrlData())) {
                ImageView imageview2 = new ImageView(mContext);

                GlideUtil.displayImage(detail.getImageUrlData(), imageview2);
                mLinearLayout.addView(imageview2);
            }


        }

    }
}
