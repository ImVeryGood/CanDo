package com.component.app.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.component.R;
import com.component.app.home.bean.ArticleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date:2019/7/31
 * describe：
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<ArticleBean.DataBean.DatasBean> list;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(List<ArticleBean.DataBean.DatasBean> dataBeanList) {
        this.list = dataBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.home_recy_item, null);
        MViewHolder holder = new MViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder mViewHolder, int i) {
        mViewHolder.title.setText(list.get(i).getTitle());
        mViewHolder.content.setText(list.get(i).getDesc());
        mViewHolder.author.setText(list.get(i).getAuthor());
        mViewHolder.bottom_author.setText(list.get(i).getAuthor());
        mViewHolder.superChapterName.setText(list.get(i).getChapterName());
        mViewHolder.day.setText(list.get(i).getNiceDate());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.superChapterName)
        TextView superChapterName;
        @BindView(R.id.author)
        TextView author;
        @BindView(R.id.bottom_author)
        TextView bottom_author;
        @BindView(R.id.day)
        TextView day;

        public MViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
