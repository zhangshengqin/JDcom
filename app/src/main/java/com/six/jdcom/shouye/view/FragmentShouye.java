package com.six.jdcom.shouye.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.six.jdcom.R;
import com.six.jdcom.shouye.adapter.KindAdapter;
import com.six.jdcom.shouye.adapter.MiaoshaAdapter;
import com.six.jdcom.shouye.adapter.TuijianAdapter;
import com.six.jdcom.shouye.adapter.Vp_kind_head_Adapter;
import com.six.jdcom.shouye.bean.BannerBean;
import com.six.jdcom.shouye.bean.KindBean;
import com.six.jdcom.shouye.presenter.BannerPrecenter;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentShouye extends Fragment implements BannerView, ViewPager.OnPageChangeListener {

    private int maxPage = 10;
    @BindView(R.id.iv_banner)
    XBanner ivBanner;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.iv_head_banner)
    ImageView ivHeadBanner;
    @BindView(R.id.vp_head_kind)
    ViewPager vpHeadKind;
    @BindView(R.id.ll_dot)
    LinearLayout llDot;
    @BindView(R.id.tv_miaosha)
    TextView tvMiaosha;
    @BindView(R.id.tv_miaosha_time)
    TextView tvMiaoshaTime;
    @BindView(R.id.tv_miaosha_shi)
    TextView tvMiaoshaShi;
    @BindView(R.id.tv_miaosha_minter)
    TextView tvMiaoshaMinter;
    @BindView(R.id.tv_miaosha_second)
    TextView tvMiaoshaSecond;
    @BindView(R.id.rcv_miaosha)
    RecyclerView rcvMiaosha;
    @BindView(R.id.tv_tuijian)
    TextView tvTuijian;
    @BindView(R.id.rcv_show)
    RecyclerView rcvShow;
    @BindView(R.id.nest_scroll)
    Auto_AlpaTitle nestScroll;
    @BindView(R.id.iv_sao)
    ImageView ivSao;
    @BindView(R.id.tv_sao)
    TextView tvSao;
    @BindView(R.id.ll_sao)
    LinearLayout llSao;
    @BindView(R.id.ll_mm)
    LinearLayout llMm;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.tool_head)
    Toolbar toolHead;
    @BindView(R.id.ll_nest_toolBar)
    LinearLayout llNestToolBar;
    Unbinder unbinder;
    private View view;
    private BannerPrecenter bannerPrecenter;
    private List<String> list_img;
    private List<ImageView> img_list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_shouye, container, false);
        unbinder = ButterKnife.bind(this, view);
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        int marig = getResources().getDimensionPixelSize(resourceId);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // initView();

    }



    @Override
    public void onResume() {
        super.onResume();


        ImageView iv_sao = (ImageView) view.findViewById(R.id.iv_sao);
        ImageView iv_msg = (ImageView) view.findViewById(R.id.iv_msg);
        TextView tv_sao = (TextView) view.findViewById(R.id.tv_sao);
        TextView tv_msg = (TextView) view.findViewById(R.id.tv_msg);
        llDot.removeAllViews();
        nestScroll.setTitleAndHead(llNestToolBar, ivBanner, iv_sao, iv_msg, tv_msg, tv_sao);
        initData();
    }

    private void initData() {
        bannerPrecenter = new BannerPrecenter(this);
        //调用precenter方法
        bannerPrecenter.gainBanner();
        bannerPrecenter.gainKind();

    }


    @Override
    public void gainSuc(final String data) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pareseData(data);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void pareseData(String data) {
        list_img = new ArrayList<>();
        Gson gson = new Gson();
        BannerBean bannerBean = gson.fromJson(data, BannerBean.class);
        if (bannerBean == null) {
            return;
        }
        //获得秒杀数据
        BannerBean.MiaoshaBean miaosha = bannerBean.getMiaosha();

        List<BannerBean.MiaoshaBean.ListBeanX> miaosha_list = miaosha.getList();
        MiaoshaAdapter miaoshaAdapter = new MiaoshaAdapter(getActivity(), miaosha_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置水平滑动
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvMiaosha.setLayoutManager(linearLayoutManager);
        rcvMiaosha.setAdapter(miaoshaAdapter);
        //获得为你推荐的数据
        BannerBean.TuijianBean tuijian = bannerBean.getTuijian();

        List<BannerBean.TuijianBean.ListBean> list = tuijian.getList();
        //设置recyclerview适配器
        TuijianAdapter tuijianAdapter = new TuijianAdapter(getActivity(), list);
        rcvShow.setLayoutManager(new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rcvShow.setAdapter(tuijianAdapter);
        String name = tuijian.getName();
        tvTuijian.setText(name);
//轮播图数据
        final List<BannerBean.DataBean> been = bannerBean.getData();
        for (int i = 0; i < been.size(); i++) {
            int type = been.get(i).getType();
            if (type == 0) {
                list_img.add(been.get(i).getIcon());

            }
        }
        ivBanner.setData(list_img, null);
        ivBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(list_img.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void gainFail() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Banner请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public void kindSuc(final String data) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //解析分类
                    pareseKindData(data);
                }
            });
        }
    }

    private void pareseKindData(String data) {
        Gson gson = new Gson();
        KindBean kindBean = gson.fromJson(data, KindBean.class);
        if (kindBean != null) {
            List<KindBean.DataBean> kind = kindBean.getData();
            Iterator<KindBean.DataBean> iterator = kind.iterator();
            while (iterator.hasNext()) {
                KindBean.DataBean next = iterator.next();
                if (next.getIshome().equals("0")) {
                    iterator.remove();
                }
            }
            if (getActivity() != null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());

                //向上取整
                int totalPage = (int) Math.ceil(kind.size() * 1.0 / maxPage);
                System.out.println("totalPage" + totalPage);
                List<View> viewList = new ArrayList<>();
                for (int i = 0; i < totalPage; i++) {
                    RecyclerView rcv = (RecyclerView) inflater.inflate(R.layout.head_rcv_kind, vpHeadKind, false);

                    rcv.setLayoutManager(new GridLayoutManager(getActivity(), 5));
                    KindAdapter kindAdapter = new KindAdapter(getActivity(), kind, i, maxPage);
                    rcv.setAdapter(kindAdapter);
                    viewList.add(rcv);
                }


                //为viewpager发送recycleview集合
                vpHeadKind.setAdapter(new Vp_kind_head_Adapter(viewList));
                //设置监听
                vpHeadKind.addOnPageChangeListener(this);

            }
        }

    }

    @Override
    public void kindFail() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "分类请求失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < 2; i++) {
            if (i == position) {
                img_list.get(i).setImageResource(R.drawable.shape_select);
            } else {
                img_list.get(i).setImageResource(R.drawable.shape_normal);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
