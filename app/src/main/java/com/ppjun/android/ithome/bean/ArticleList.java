package com.ppjun.android.ithome.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Package :com.ppjun.android.ithome.bean
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 15:11.
 */

public class ArticleList {
    String title;
    String createTime;
    String img;
    String onClickUrl;
    String content;
    String tag;

    public String getContent() {


        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTag() {

       List<String> list=new ArrayList<>();
        String[] allTag=tag.split("：")[1].split("，");

        for (int i = 0; i < allTag.length; i++) {
            list.add(allTag[i]);
        }


        return list;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOnClickUrl() {
        return onClickUrl;
    }

    public void setOnClickUrl(String onClickUrl) {
        this.onClickUrl = onClickUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", img='" + img + '\'' +
                ", onClickUrl='" + onClickUrl + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
