package com.six.jdcom.utils;

import android.app.Application;
import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.mob.MobSDK;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by 额头发 on 2017/11/13.
 */

public class App extends Application{
    private static OkHttpClient okHttpClient;
    @Override
    public void onCreate() {
        super.onCreate();

        MobSDK.init(this, "221154230f54e", "47d25a743ada2064c8537b36801099ca");
        //ImageLoader缓存图片的路劲
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/yuekao");
        //设置ImageLoader加载图片的属性
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(1 * 1024 * 1024)
                .memoryCacheExtraOptions(50, 50)
                .threadPoolSize(3)
                .threadPriority(100)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCache(new UnlimitedDiskCache(file))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        //初始化ImageLoader
        ImageLoader.getInstance().init(build);
        //实例化OkHttpClient,并设置拦截器
        okHttpClient = new OkHttpClient();
        okHttpClient = okHttpClient.newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new MyLogInterceptor())
                .build();

    }

    //OkHttpClient 的拦截器
    //拦截器,可以修改header,可以通过拦截器打印日志
    public class MyLogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .header("shenfenyanzheng", "zhangsan")
                    .build();
            HttpUrl url = request.url();
            String httpUrl = url.url().toString();
            Log.e("请求地址:", httpUrl);
            Response response = chain.proceed(request);
            int code = response.code();
            Log.e("请求返回码:", code + "");
            return response;
        }
    }

    //封装的调用OkHttpClient的类
    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;

    }
}
