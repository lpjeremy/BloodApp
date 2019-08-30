package com.lpjeremy.libmodule.http;

import android.util.Log;
import com.blankj.utilcode.util.SPUtils;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @desc:可配置baseURL的Retrofit工具类
 * @date:2019/7/13 14:39
 * @auther:lp
 * @version:1.1.6
 */
public class RetrofitUtils {
    private static final String HTTP_TAG = "http";

    private Retrofit mRetrofit;

    private static class RetrofitUtilsHolder {
        private static final RetrofitUtils InStance = new RetrofitUtils();
    }

    public static final RetrofitUtils getInstance() {
        return RetrofitUtilsHolder.InStance;
    }

    /**
     * 创建默认Retrofit
     *
     * @param baseUrl
     */
    public RetrofitUtils createBasicsRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                    .addConverterFactory(GsonConverterFactory.create())//使用Gson
                    .client(createOkHttpClient())
                    .build();
        }
        return RetrofitUtilsHolder.InStance;
    }

    /**
     * 创建指定的ApiService
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T createApiService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    /**
     * 创建OkHttpClient
     *
     * @return
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient mOkHttpClient = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder())
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(createHttpLoggingInterceptor())
                .addInterceptor(createInterceptor())
                .build();
        return mOkHttpClient;
    }

    private Interceptor createInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", "Bearer " + getToken())
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 创建HttpLoggingInterceptor
     * 配合com.squareup.okhttp3:logging-interceptor:3.4.1使用
     *
     * @return HttpLoggingInterceptor
     */
    private HttpLoggingInterceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(HTTP_TAG, "requestBack: " + message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private String getToken() {
        return SPUtils.getInstance().getString("HIS_TOKEN");
    }
}
