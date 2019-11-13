package com.hysyyl.bloodapp.data.http;

import com.hysyyl.bloodapp.BuildConfig;
import com.hysyyl.bloodapp.data.http.api.*;
import com.hysyyl.bloodapp.model.*;
import com.hysyyl.bloodapp.model.request.RegisterParams;
import com.hysyyl.bloodapp.model.request.VideoRequest;
import com.hysyyl.bloodapp.model.result.VideoResult;
import com.lpjeremy.libmodule.http.HttpPresenterKT;
import com.lpjeremy.libmodule.http.RetrofitUtils;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import java.util.List;

/**
 * @desc:接口请求工具类
 * @date:2019/8/19 17:57
 * @auther:lp
 * @version:1.1.6
 */
public class HttpUtils extends HttpPresenterKT implements VideoApi, UserApi, BaseApi, CustomerApi, OrderApi, ReportApi,AccountApi {
    BaseApiService mBaseApiService;
    UserApiService mUserApiService;
    CustomerApiService mCustomerApiService;
    OrderApiService mOrderApiService;
    ReportApiService mReportApiService;
    VideoApiService mVideoApiService;
    AccountApiService mAccountApiService;



    private static class HttpUtilsHolder {
        public static final HttpUtils inStance = new HttpUtils();
    }

    public static final HttpUtils getInstance() {
        return HttpUtilsHolder.inStance;
    }

    public HttpUtils() {
        RetrofitUrlManager.getInstance().setDebug(BuildConfig.DEBUG);

        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.LP_ACCOUNT, HttpConfig.URL.LP_ACCOUNT_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.VIDEO_Y, HttpConfig.URL.VIDEO_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.HIS_BASE, HttpConfig.URL.BASE_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.HIS_USER, HttpConfig.URL.USER_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.HIS_CUSTOMER, HttpConfig.URL.CUSTOMER_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.HIS_ORDER, HttpConfig.URL.ORDER_URL);
        RetrofitUrlManager.getInstance().putDomain(HttpConfig.CONSTANTS.HIS_REPORT, HttpConfig.URL.REPORT_URL);

        mBaseApiService = RetrofitUtils.getInstance().createBasicsRetrofit(HttpConfig.URL.BASE_URL).createApiService(BaseApiService.class);
        mUserApiService = RetrofitUtils.getInstance().createApiService(UserApiService.class);
        mCustomerApiService = RetrofitUtils.getInstance().createApiService(CustomerApiService.class);
        mOrderApiService = RetrofitUtils.getInstance().createApiService(OrderApiService.class);
        mReportApiService = RetrofitUtils.getInstance().createApiService(ReportApiService.class);
        mVideoApiService = RetrofitUtils.getInstance().createApiService(VideoApiService.class);
        mAccountApiService = RetrofitUtils.getInstance().createApiService(AccountApiService.class);
    }

    @Override
    public void sendCode(String phone, HttpRequestCallBackKT<String> callBack) {
        execute(mBaseApiService.sendCode(phone), callBack);
    }

    @Override
    public void login(String phone, String pwd, int loginMode, HttpRequestCallBackKT<LoginResult> callBack) {
        execute(mUserApiService.login(phone, pwd, loginMode), callBack);
    }

    @Override
    public void getUserInfo(HttpRequestCallBackKT<UserInfo> callBack) {
        execute(mCustomerApiService.getUserInfo(), callBack);
    }

    @Override
    public void getUserBalance(HttpRequestCallBackKT<Account> callBack) {
        execute(mCustomerApiService.getUserBalance(), callBack);
    }

    @Override
    public void getUserSaler(HttpRequestCallBackKT<Saler> callBack) {
        execute(mCustomerApiService.getUserSaler(), callBack);
    }

    @Override
    public void checkBindWechat(HttpRequestCallBackKT<String> callBack) {
        execute(mUserApiService.checkBindWechat(), callBack);
    }

    @Override
    public void getUserOrderList(String keyWords, String orderStatus, int pageIndex, int pageSize, HttpRequestCallBackKT<List<Order>> callBack) {
        execute(mOrderApiService.getUserOrderList(keyWords, orderStatus, pageIndex, pageSize), callBack);
    }

    @Override
    public void loadReportData(String keyWords, int pageIndex, int pageSize, HttpRequestCallBackKT<List<Report>> callBack) {
        execute(mReportApiService.getReportListData(keyWords, pageIndex, pageSize), callBack);
    }

    @Override
    public void getVideoList(VideoRequest videoRequest, HttpRequestCallBackKT<List<Video>> callBack) {
        execute(mVideoApiService.getVideoList(videoRequest), callBack);
    }

    @Override
    public void getVideoData(VideoRequest videoRequest, HttpRequestCallBackKT<VideoResult> callBack) {
        executeOther(mVideoApiService.getVideoData(videoRequest), callBack);
    }

    @Override
    public void register(String userName, String password, HttpRequestCallBackKT<String> callBack) {
        executeJava(mAccountApiService.register(userName,password),callBack);
    }

    @Override
    public void register(RegisterParams params, HttpRequestCallBackKT<String> callBack) {
        executeJava(mAccountApiService.register(params),callBack);
    }
}
