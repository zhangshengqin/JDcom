package com.six.jdcom.shouye.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.six.jdcom.R;
import com.six.jdcom.shouye.bean.KindBean;

import java.util.List;
/**
 * Created by 额头发 on 2017/11/14.
 */

public class KindAdapter extends RecyclerView.Adapter<KindAdapter.MyViewHolder>{
    private final Context context;
    private final List<KindBean.DataBean> list;

    private int mIndex;//页数下标，表示第几页，从0开始
    private int mPagerSize;

    public KindAdapter(Context context, List<KindBean.DataBean> list, int mIndex, int mPagerSize) {
        this.list = list;
        this.context = context;
        this.mIndex = mIndex;
        this.mPagerSize = mPagerSize;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rcb_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        position = position + mIndex * mPagerSize;
        holder.tv_rcb.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.iv_rcb);
    }

    @Override
    public int getItemCount() {
        return list.size() > (mIndex + 1) * mPagerSize ? mPagerSize : (list.size() - mIndex * mPagerSize);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_rcb;
        private final TextView tv_rcb;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv_rcb = (ImageView) itemView.findViewById(R.id.iv_rcb_kind);
            tv_rcb = (TextView) itemView.findViewById(R.id.tv_rcb_kind);
        }
    }
}
