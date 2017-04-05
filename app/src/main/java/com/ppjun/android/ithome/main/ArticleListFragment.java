package com.ppjun.android.ithome.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ppjun.android.ithome.articleInfo.ArticleInfoActivity;
import com.ppjun.android.ithome.R;
import com.ppjun.android.ithome.adapter.ArticleListAdapter;
import com.ppjun.android.ithome.bean.ArticleList;
import com.ppjun.android.ithome.ithome.HomeUrl;
import com.ppjun.android.ithome.listener.onItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ppjun.android.ithome.ithome.Constant.BUNDLE_KEY;
import static com.ppjun.android.ithome.ithome.Constant.CATEGORY_KEY;
import static com.ppjun.android.ithome.ithome.Constant.ONCLICK_KEY;
import static com.ppjun.android.ithome.ithome.Constant.PREFIX_KEY;

/**
 * Package :com.ppjun.android.ithome.article
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 11:45.
 */

public class ArticleListFragment extends Fragment implements MainContract.View {

    MainContract.Presenter mPresenter;
    @BindView(R.id.main_rv)
    XRecyclerView mXReyclerView;
    Context mContext;
    ArticleListAdapter mArticleListAdapter;
    List<ArticleList> mList = new ArrayList<>();

    int page = 1;
    boolean isLoadMore;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_article_rv, null);
        ButterKnife.bind(this, view);
        mContext = getActivity();

        String url = getArguments().getString(BUNDLE_KEY, HomeUrl.ANDROIDURL);
        String prefixUrl = getArguments().getString(PREFIX_KEY, HomeUrl.ANDROID_PREFIX);
        String category = getArguments().getString(CATEGORY_KEY, HomeUrl.ANDROID);

        new MainPresenter(this);

        init(url, prefixUrl, category);
        return view;
    }

    private void init(final String url, final String prefixUrl, final String category) {

        mXReyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mXReyclerView.setAdapter(mArticleListAdapter = new ArticleListAdapter(mList));
        mXReyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isLoadMore = false;
                mPresenter.getArticleList(url, page);
                mXReyclerView.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                page++;
                isLoadMore = true;
                mPresenter.loadMoreArticleList(url, page, prefixUrl, category);
                mXReyclerView.loadMoreComplete();

            }
        });
        mXReyclerView.refresh();
        mArticleListAdapter.setOnItemClickListener(new onItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(mContext, ArticleInfoActivity.class);
                intent.putExtra(ONCLICK_KEY, mList.get(position).getOnClickUrl());
                startActivity(intent);
            }
        });


    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void setRecyclerViewData(List<ArticleList> list) {
        if (!isLoadMore) {
            mList.clear();
        }
            mList.addAll(list);
            mArticleListAdapter.notifyDataSetChanged();




    }
}
