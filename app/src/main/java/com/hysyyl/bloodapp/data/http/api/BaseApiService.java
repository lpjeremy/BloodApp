package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.data.http.HttpConfig;
import com.lpjeremy.libmodule.http.model.BaseResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * @desc:服务器api接口
 * @date:2019/7/13 15:42
 * @auther:lp
 * @version:1.1.6
 */
public interface BaseApiService {
    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_BASE})
    /**
     *发送验证码
     */
    @GET(HttpConfig.APINAME.sendCode)
    Observable<BaseResult<String>> sendCode(@Query("Phone") String phone);
}
