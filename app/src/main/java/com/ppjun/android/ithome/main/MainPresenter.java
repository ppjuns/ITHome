package com.ppjun.android.ithome.main;

import android.util.Log;

import com.ppjun.android.ithome.bean.ArticleList;
import com.ppjun.android.ithome.ithome.Home;
import com.ppjun.android.ithome.ithome.HomeUrl;

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

import static com.ppjun.android.ithome.ithome.HomeUrl.MAIN_LOAD_MORE_URL;

/**
 * Package :com.ppjun.android.ithome.main
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 11:32.
 */

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();
    MainContract.View view;


    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }


    @Override
    public void getArticleList(final String url, int i) {
        String newUrl;

        if (url.equals(HomeUrl.MAINURL)) {
            newUrl = url;

        } else {
            newUrl = url + i + HomeUrl.URL_END;
        }


        Home.getInstance().getService().loadArticleList(newUrl).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<List<ArticleList>>>() {
                             @Override
                             public ObservableSource<List<ArticleList>> apply(@NonNull String s) throws Exception {
                                 return Observable.just(parseByJsoup(s, isSameUrl(url), false));
                             }
                         }
                ).subscribe(new DefaultObserver<List<ArticleList>>() {
            @Override
            public void onNext(List<ArticleList> articleLists) {
                view.setRecyclerViewData(articleLists);
            }

            @Override
            public void onError(Throwable e) {
                //找数据库
            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public void loadMoreArticleList(final String url, int i, String prefixUrl, String categoryid) {

        String newUrl;
        String type;


        if (url.equals(HomeUrl.MAINURL)) {
            newUrl = MAIN_LOAD_MORE_URL;

            type = "indexpage";

        } else {
            newUrl = prefixUrl + HomeUrl.LOADMORE_SUFFIX;
            type = "pccategorypage";
        }


        Home.getInstance().getService().loadMoreArticleList(newUrl, type, String.valueOf(i), categoryid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<String, ObservableSource<List<ArticleList>>>() {
                    @Override
                    public ObservableSource<List<ArticleList>> apply(@NonNull String s) throws Exception {
                        return Observable.just(parseByJsoup(s, isSameUrl(url), true));
                    }
                }).subscribe(new DefaultObserver<List<ArticleList>>() {
            @Override
            public void onNext(List<ArticleList> articleLists) {
                view.setRecyclerViewData(articleLists);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }


    private List<ArticleList> parseByJsoup(String response, boolean isMain, boolean isLoadMore) {

        List<ArticleList> informationList = new ArrayList<>();
        Document doc = Jsoup.parse(response);
        Elements blogList;
        if (isLoadMore) {
            if (isMain) {
                blogList = doc.getElementsByClass("cate_list").select("li");
            } else {
                blogList = doc.select("li");
            }


        } else {


            blogList = doc.getElementsByClass("cate_list").select("li");
        }


        for (Element blogItem : blogList) {
            ArticleList information = new ArticleList();
            String title = blogItem.select("h2").select("a").text();
            String datetime = blogItem.select("h2").select("span").text();
            String url = blogItem.select("h2").select("a").attr("href");
            String img_url;
            if (isLoadMore) {
                img_url = blogItem.select("a").select("img").attr("src");
            } else {
                if (isMain) {
                    img_url = blogItem.select("a").select("img").attr("src");
                } else {
                    img_url = blogItem.select("a").select("img").attr("data-original");
                }
            }


            String content = blogItem.select("p").text();
            String tags;
            if (isMain) {
                tags = "关键词：A，B";
            } else {
                tags = blogItem.getElementsByClass("tags").text();
            }


            information.setTitle(title);
            information.setCreateTime(datetime);
            information.setImg(img_url);
            information.setOnClickUrl(url);
            information.setContent(content);
            information.setTag(tags);

            if (!"".equals(information.getImg()))
                informationList.add(information);
        }


        return informationList;
    }

    private boolean isSameUrl(String url) {
        return url.equals(HomeUrl.MAINURL) ? true : false;
    }
}