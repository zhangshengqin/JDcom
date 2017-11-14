package com.six.jdcom.wode.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.six.jdcom.R;
import com.six.jdcom.wode.adapter.MyFragmentSJ;
import com.six.jdcom.wode.adapter.MyFragmentXBBbb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentWode extends Fragment {
    @BindView(R.id.my_title_img)
    ImageView myTitleImg;
    @BindView(R.id.my_message_imagebtn)
    ImageView myMessageImagebtn;
    @BindView(R.id.my_settings_imagebtn)
    ImageView mySettingsImagebtn;
    @BindView(R.id.my_linearlayout)
    RelativeLayout myLinearlayout;
    @BindView(R.id.my_recycle_one)
    RecyclerView myRecycleOne;
    @BindView(R.id.my_recycle_two)
    RecyclerView myRecycleTwo;
    Unbinder unbinder;
//    private RecyclerView my_recycle_one;
//    private RelativeLayout layout;

    //    private RecyclerView my_recycle_two;
    private MyFragmentXBBbb twoadapter;
    //    private ImageView my_title_img;
    private static final int REQUESTCODE = 520;
    private boolean iflogin;
    private MyFragmentSJ oneadapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_wode, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//设置第一个recyclerview的适配器和item点击事件
//        initview();
        GridLayoutManager gmanager = new GridLayoutManager(getActivity(), 5);
        myRecycleOne.setLayoutManager(gmanager);
        oneadapter = new MyFragmentSJ();
        myRecycleOne.setAdapter(oneadapter);


        oneadapter.setOnItemTwoClickLintener(new MyFragmentSJ.OnItemTwoClickLintener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (iflogin) {
                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), RegActivity.class);
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
        //点击头部实现跳转登陆页面
        myLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });
        //设置第二个recyclerview的适配器和item点击事件
        GridLayoutManager gmanagertwo = new GridLayoutManager(getActivity(), 4);
        myRecycleTwo.setLayoutManager(gmanagertwo);
        twoadapter = new MyFragmentXBBbb();
        myRecycleTwo.setAdapter(twoadapter);
        twoadapter.setOnItemOneClickLintener(new MyFragmentXBBbb.OnItemOneClickLintener() {
            @Override
            public void OnItemClick(View view, int position) {
                if (iflogin) {
                    Toast.makeText(getContext(), "已经登陆了", Toast.LENGTH_SHORT).show();
                    /*Intent intent=new Intent();
                    startActivity(intent);*/
                } else {
                    Intent intent = new Intent(getActivity(), RegActivity.class);
                    startActivityForResult(intent, REQUESTCODE);
                }
            }
        });
    }

//    private void initview() {
//
//        layout = (RelativeLayout) view.findViewById(R.id.my_linearlayout);
//        my_recycle_one = (RecyclerView) view.findViewById(R.id.my_recycle_one);
//        my_recycle_two = (RecyclerView) view.findViewById(R.id.my_recycle_two);
//        my_title_img = (ImageView) view.findViewById(R.id.my_title_img);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 520 && resultCode == 521) {
            String imgtitle = data.getStringExtra("imgtitle");
            iflogin = data.getBooleanExtra("iflogin", false);
            loadCirclePic(getContext(), imgtitle, myTitleImg);
        }
    }

    public static void loadCirclePic(final Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.b3h)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
