package com.ppjun.android.ithome.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppjun.android.ithome.R;
import com.ppjun.android.ithome.bean.ArticleList;
import com.ppjun.android.ithome.listener.onItemClickListener;
import com.ppjun.android.ithome.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Package :com.ppjun.android.ithome.adapter
 * Description :
 * Author :Rc3
 * Created at :2017/3/23 15:10.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListHolder> {
    private static final String TAG = ArticleListAdapter.class.getSimpleName();
    public onItemClickListener mOnItemClickListener;
    List<ArticleList> mList;


    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public ArticleListAdapter(List<ArticleList> list) {
        this.mList = list;
    }

    @Override
    public ArticleListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_article_rv_item, null);
        return new ArticleListHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleListHolder holder, final int position) {

        ArticleList articleList = mList.get(position);
        holder.mTitle.setText(articleList.getTitle());
        holder.mCreateTime.setText(articleList.getCreateTime());
        holder.mContent.setText(articleList.getContent());
        GlideUtil.displayImage(articleList.getImg(), holder.mImageView, true);

        if (articleList.getTag().get(0).equals("A")) {
            holder.mTag1.setVisibility(View.GONE);
            holder.mTag2.setVisibility(View.GONE);
        } else {
            holder.mTag2.setText(articleList.getTag().get(0));
            if (articleList.getTag().size() > 1) {


                holder.mTag1.setText(articleList.getTag().get(1));


            } else {
                holder.mTag1.setVisibility(View.GONE);
            }

        }

        holder.itemView.setTag(articleList);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ArticleListHolder extends RecyclerView.ViewHolder {
        View itemView;
        @BindView(R.id.main_article_img)
        ImageView mImageView;
        @BindView(R.id.main_article_title)
        TextView mTitle;
        @BindView(R.id.main_article_createtime)
        TextView mCreateTime;
        @BindView(R.id.main_article_content)
        TextView mContent;
        @BindView(R.id.main_article_tag1)
        TextView mTag1;
        @BindView(R.id.main_article_tag2)
        TextView mTag2;

        public ArticleListHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
