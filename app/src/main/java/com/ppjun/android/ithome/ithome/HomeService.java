package com.ppjun.android.ithome.ithome;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Package :com.ppjun.android.ithome.ithome
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 14:32.
 */

public interface HomeService {

    @GET
    Observable<String> loadArticleList(@Url String url);

    @POST
    @FormUrlEncoded
    Observable<String> loadMoreArticleList(@Url String url, @Field("type")String type,@Field("page")String page,@Field("categoryid")String categoryid);

    @GET
    Observable<String> loadArticleInfo(@Url String url);
}
