package com.six.jdcom.shouye.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 额头发 on 2017/11/14.
 */

public class Vp_kind_head_Adapter extends PagerAdapter{
    private List<View> viewLists;//View就是GridView

    public Vp_kind_head_Adapter(List<View> viewLists) {
        this.viewLists = viewLists;
    }
    @Override
    public int getCount() {
        return viewLists != null ? viewLists.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
