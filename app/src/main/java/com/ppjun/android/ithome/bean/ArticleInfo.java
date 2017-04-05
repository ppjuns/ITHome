package com.ppjun.android.ithome.bean;

import java.util.List;

/**
 * Package :com.ppjun.android.ithome.bean
 * Description :
 * Author :Rc3
 * Created at :2017/04/05 14:22.
 */

public class ArticleInfo {


    String title;
    String createTime;
    List<ArticleDetail> list;

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

    public List<ArticleDetail> getList() {
        return list;
    }

    public void setList(List<ArticleDetail> list) {
        this.list = list;
    }

    public class ArticleDetail {


        String content;
        String imageUrlSrc;
        String imageUrlData;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImageUrlSrc() {
            return imageUrlSrc;
        }

        public void setImageUrlSrc(String imageUrlSrc) {
            this.imageUrlSrc = imageUrlSrc;
        }

        public String getImageUrlData() {
            return imageUrlData;
        }

        public void setImageUrlData(String imageUrlData) {
            this.imageUrlData = imageUrlData;
        }
    }
}