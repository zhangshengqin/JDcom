package com.six.jdcom.shouye.presenter;

import com.six.jdcom.shouye.model.BannerModel;
import com.six.jdcom.shouye.view.BannerView;

/**
 * Created by 额头发 on 2017/11/14.
 */

public class BannerPrecenter implements BannerModel.BannerListener{
    private final BannerView bannerView;
    private final BannerModel bannerModel;

    public BannerPrecenter(BannerView bannerView) {
        this.bannerView = bannerView;
        bannerModel = new BannerModel();
        bannerModel.setBanner(this);
    }

    public void gainBanner() {
        bannerModel.gainImg();

    }

    public void gainKind() {
        bannerModel.gainKind();
    }

    @Override
    public void gainSuc(String data) {
        bannerView.gainSuc(data);
    }

    @Override
    public void gainFail() {
        bannerView.gainFail();
    }

    @Override
    public void kindSuc(String data) {
        bannerView.kindSuc(data);

    }

    @Override
    public void kindFail() {
        bannerView.kindFail();
    }
}
