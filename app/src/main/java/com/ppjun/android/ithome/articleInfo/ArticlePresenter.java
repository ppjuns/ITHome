package com.ppjun.android.ithome.articleInfo;

import android.text.TextUtils;

import com.ppjun.android.ithome.bean.ArticleInfo;
import com.ppjun.android.ithome.ithome.Home;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Package :com.ppjun.android.ithome.articleInfo
 * Description :
 * Author :Rc3
 * Created at :2017/04/05 14:13.
 */

public class ArticlePresenter implements ArticleInfoContract.Presenter {

    ArticleInfoContract.View mView;
    ArticleInfo mArticleInfo = new ArticleInfo();

    public ArticlePresenter(ArticleInfoContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void requestData(String url) {
        Home.getInstance().getService().loadArticleInfo(url).
                flatMap(new Function<String, ObservableSource<ArticleInfo>>() {
                    @Override
                    public ObservableSource<ArticleInfo> apply(@NonNull String s) throws Exception {
                        return Observable.just(parse(s));
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ArticleInfo>() {
                    @Override
                    public void onNext(ArticleInfo articleInfos) {
                        mView.setData(articleInfos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private ArticleInfo parse(String response) {


        Document doc = Jsoup.parse(response);

        mArticleInfo.setTitle(doc.getElementsByClass("post_title").select("h1").text());
        mArticleInfo.setCreateTime(doc.getElementsByClass("post_title").select("span").get(1).text());


        Elements contents = doc.getElementsByClass("post_content").select("p");
        List<ArticleInfo.ArticleDetail> list=new ArrayList<>();
        for (final Element content : contents) {
            ArticleInfo.ArticleDetail detail=   mArticleInfo.new ArticleDetail();
            String cc = content.text();

            String img1 = content.select("img").attr("src");
            String img2 = content.select("img").attr("data-original");

            if (!TextUtils.isEmpty(cc)) {
                detail.setContent("\t\t" + cc);

            }

            if (!TextUtils.isEmpty(img1)) {



                detail.setImageUrlSrc(img1);

            }
            if (!TextUtils.isEmpty(img2)) {
                detail.setImageUrlSrc(img2);


            }
            list.add(detail);
        }
        mArticleInfo.setList(list);
        return mArticleInfo;
    }
}
