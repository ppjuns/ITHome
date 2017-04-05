package com.ppjun.android.ithome.main;

import com.ppjun.android.ithome.base.BasePresenter;
import com.ppjun.android.ithome.base.BaseView;
import com.ppjun.android.ithome.bean.ArticleList;

import java.util.List;

/**
 * Package :com.ppjun.android.ithome.main
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 11:31.
 */

public interface MainContract {
    interface Presenter extends BasePresenter {
        void getArticleList(String url, int i);

        void loadMoreArticleList(String url, int i, String prefix, String categoryid);
    }

    interface View extends BaseView<Presenter> {


        void setRecyclerViewData(List<ArticleList> list);
    }
}
