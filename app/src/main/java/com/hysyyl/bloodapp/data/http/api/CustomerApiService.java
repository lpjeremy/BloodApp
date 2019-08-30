package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.data.http.HttpConfig;
import com.hysyyl.bloodapp.model.Account;
import com.hysyyl.bloodapp.model.Saler;
import com.hysyyl.bloodapp.model.UserInfo;
import com.lpjeremy.libmodule.http.model.BaseResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @desc:用户登录部分接口
 * @date:2019/7/13 16:17
 * @auther:lp
 * @version:1.1.6
 */
public interface CustomerApiService {
    //如果此部分接口需要使用和baseUrl接口地址不一样的其他地址，则设置下面这句话，加上要用到的接口地址

    /**
     * 获取用户信息
     */
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_CUSTOMER})
    @GET(HttpConfig.APINAME.getUserInfo)
    Observable<BaseResult<UserInfo>> getUserInfo();

    /**
     * 获取用户账户信息
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_CUSTOMER})
    @GET(HttpConfig.APINAME.getUserBalance)
    Observable<BaseResult<Account>> getUserBalance();
    /**
     * 获取用户业务员
     * @return
     */
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_CUSTOMER})
    @GET(HttpConfig.APINAME.getUserSaler)
    Observable<BaseResult<Saler>> getUserSaler();
}
