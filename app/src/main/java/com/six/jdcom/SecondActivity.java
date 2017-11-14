package com.six.jdcom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.six.jdcom.fenlei.view.FragmentFenlei;
import com.six.jdcom.gouwuche.view.FragmentGouwuche;
import com.six.jdcom.shouye.view.FragmentShouye;
import com.six.jdcom.wode.view.FragmentWode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);


        bottomTabBar.init(getSupportFragmentManager())
                .addTabItem("首页",R.mipmap.ic_nav_home, FragmentShouye.class)
                .addTabItem("分类",R.mipmap.ic_nav_class, FragmentFenlei.class)
                .addTabItem("购物车",R.mipmap.ic_nav_cart, FragmentGouwuche.class)
                .addTabItem("我的",R.mipmap.ic_nav_user, FragmentWode.class);


    }
}
