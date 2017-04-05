package com.ppjun.android.ithome.articleInfo;

import com.ppjun.android.ithome.base.BasePresenter;
import com.ppjun.android.ithome.base.BaseView;
import com.ppjun.android.ithome.bean.ArticleInfo;

/**
 * Package :com.ppjun.android.ithome.DetailInfo
 * Description :
 * Author :Rc3
 * Created at :2017/3/28 18:44.
 */

public interface ArticleInfoContract  {
     interface Presenter extends BasePresenter{
         void requestData(String url);
     }
    interface View extends BaseView<ArticleInfoContract.Presenter>{
      void setData(ArticleInfo info);
    }
}
