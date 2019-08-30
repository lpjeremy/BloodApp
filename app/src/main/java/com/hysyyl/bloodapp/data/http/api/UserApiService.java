package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.data.http.HttpConfig;
import com.hysyyl.bloodapp.model.LoginResult;
import com.lpjeremy.libmodule.http.model.BaseResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @desc:用户登录部分接口
 * @date:2019/7/13 16:17
 * @auther:lp
 * @version:1.1.6
 */
public interface UserApiService {
    //如果此部分接口需要使用和baseUrl接口地址不一样的其他地址，则设置下面这句话，加上要用到的接口地址


    /**
     * 登录接口
     *
     * @param phone
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_USER})
    @GET(HttpConfig.APINAME.login)
    Observable<BaseResult<LoginResult>> login(@Query("Phone") String phone, @Query("Voucher") String password, @Query("LoginMode") int mode);


    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_USER})
    @GET(HttpConfig.APINAME.checkBindWeChat)
    Observable<BaseResult<String>> checkBindWechat();
}
