package com.six.jdcom.wode.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 额头发 on 2017/11/13.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.six.jdcom.R;
import com.six.jdcom.wode.bean.MyFragmentSjBeann;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentSJ extends RecyclerView.Adapter<MyFragmentSJ.MyOnViewHolder>{
    private List<MyFragmentSjBeann> list;
    private OnItemTwoClickLintener onelintener;

    public MyFragmentSJ() {
        initdata();
    }

    private void initdata() {
        list = new ArrayList<>();
        list.add(new MyFragmentSjBeann("待付款", R.drawable.fukuan));
        list.add(new MyFragmentSjBeann("待收货", R.drawable.shouhou));
        list.add(new MyFragmentSjBeann("待评价", R.drawable.pinjia));
        list.add(new MyFragmentSjBeann("退换/售后", R.drawable.shouhou));
        list.add(new MyFragmentSjBeann("我的订单", R.drawable.dingdan));
        list.add(new MyFragmentSjBeann("京豆", 0));
        list.add(new MyFragmentSjBeann("优惠券", 0));
        list.add(new MyFragmentSjBeann("白条", 0));
        list.add(new MyFragmentSjBeann("京东E卡", 0));
        list.add(new MyFragmentSjBeann("京东E卡", R.drawable.qianbao));
    }

    @Override
    public MyFragmentSJ.MyOnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revycler_one, parent, false);
        return new MyOnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyFragmentSJ.MyOnViewHolder holder, final int position) {
        holder.one_recycler_content.setText(list.get(position).getName());
        if (list.get(position).getName().equals("京豆")) {
            holder.one_recycler_img.setText("" + list.get(position).getImgresources());
        } else if (list.get(position).getName().equals("优惠券")) {
            holder.one_recycler_img.setText("" + list.get(position).getImgresources());
        } else if (list.get(position).getName().equals("白条")) {
            holder.one_recycler_img.setText("" + list.get(position).getImgresources());
        } else if (list.get(position).getName().equals("京东E卡")) {
            String s = String.format("%.2f", (float) list.get(position).getImgresources());
            holder.one_recycler_img.setText(s);
        } else {
            holder.one_recycler_img.setBackgroundResource(list.get(position).getImgresources());
        }
        holder.myoneclickitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onelintener.OnItemClick(v, position);
            }
        });
    }

    public interface OnItemTwoClickLintener {
        void OnItemClick(View view, int position);
    }

    public void setOnItemTwoClickLintener(MyFragmentSJ.OnItemTwoClickLintener lintener) {
        onelintener = lintener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyOnViewHolder extends RecyclerView.ViewHolder {
        private TextView one_recycler_img;
        private TextView one_recycler_content;
        private LinearLayout myoneclickitem;

        public MyOnViewHolder(View itemView) {
            super(itemView);
            one_recycler_img = (TextView) itemView.findViewById(R.id.one_recycler_img);
            one_recycler_content = (TextView) itemView.findViewById(R.id.one_recycler_content);
            myoneclickitem = (LinearLayout) itemView.findViewById(R.id.myoneclickitem);
        }
    }
}
