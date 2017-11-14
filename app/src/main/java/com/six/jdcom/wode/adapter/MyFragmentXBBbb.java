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
import com.six.jdcom.wode.bean.MyFragmentXBBb;

import java.util.ArrayList;
import java.util.List;


public class MyFragmentXBBbb extends RecyclerView.Adapter<MyFragmentXBBbb.MyTwoViewHolder>{
    private OnItemOneClickLintener onelintener;
    private List<MyFragmentXBBb> list;

    public MyFragmentXBBbb() {
        initdatatwo();
    }

    public interface OnItemOneClickLintener {
        void OnItemClick(View view, int position);
    }

    public void setOnItemOneClickLintener(OnItemOneClickLintener lintener) {
        onelintener = lintener;
    }

    private void initdatatwo() {
        list = new ArrayList<>();
        list.add(new MyFragmentXBBb("商品关注", R.drawable.shangping, ""));
        list.add(new MyFragmentXBBb("店铺关注", R.drawable.dianpu, ""));
        list.add(new MyFragmentXBBb("内容关注", R.drawable.guanzhu, ""));
        list.add(new MyFragmentXBBb("浏览记录", R.drawable.jilu, ""));
        list.add(new MyFragmentXBBb("我的活动", R.drawable.wohuodong, "预约/拍卖等"));
        list.add(new MyFragmentXBBb("社区", R.drawable.shequ, "有料有福利"));
        list.add(new MyFragmentXBBb("客户服务", R.drawable.kehu, ""));
    }


    @Override
    public MyTwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revycler_two, parent, false);
        return new MyTwoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyTwoViewHolder holder, final int position) {
        holder.two_recycler_content.setText(list.get(position).getTwoname());
        holder.two_bottom_tv.setText(list.get(position).getBottomname());
        holder.two_recycler_img.setBackgroundResource(list.get(position).getTwoimgresources());
        holder.two_item_onclik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onelintener.OnItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyTwoViewHolder extends RecyclerView.ViewHolder {
        private TextView two_bottom_tv;
        private TextView two_recycler_content;
        private TextView two_recycler_img;
        private LinearLayout two_item_onclik;

        public MyTwoViewHolder(View itemView) {
            super(itemView);
            two_bottom_tv = (TextView) itemView.findViewById(R.id.two_bottom_tv);
            two_recycler_content = (TextView) itemView.findViewById(R.id.two_recycler_content);
            two_recycler_img = (TextView) itemView.findViewById(R.id.two_recycler_img);
            two_item_onclik = (LinearLayout) itemView.findViewById(R.id.two_item_onclik);
        }
    }
}
