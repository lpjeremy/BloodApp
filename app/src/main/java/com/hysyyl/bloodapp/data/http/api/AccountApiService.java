package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.data.http.HttpConfig;
import com.hysyyl.bloodapp.model.LoginResult;
import com.lpjeremy.libmodule.http.model.BaseResult;
import com.lpjeremy.libmodule.http.model.BaseResultJava;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;
/**
 * @desc:自己写的服务器接口的账户部分api
 * @date:2019/11/13 16:03
 * @auther:lp
 * @version:1.1.6
 */
public interface AccountApiService {
    //如果此部分接口需要使用和baseUrl接口地址不一样的其他地址，则设置下面这句话，加上要用到的接口地址


    /**
     * 注册接口
     *
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.LP_ACCOUNT})
    @GET(HttpConfig.APINAME.lpLogin)
    Observable<BaseResultJava<String>> register(@Query("userName") String userName, @Query("password") String password);

}
