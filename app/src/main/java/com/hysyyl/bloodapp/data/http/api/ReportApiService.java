package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.data.http.HttpConfig;
import com.hysyyl.bloodapp.model.Report;
import com.lpjeremy.libmodule.http.model.BaseResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

public interface ReportApiService {
    //如果此部分接口需要使用和baseUrl接口地址不一样的其他地址，则设置下面这句话，加上要用到的接口地址

    @Headers({DOMAIN_NAME_HEADER + HttpConfig.CONSTANTS.HIS_REPORT})
    @GET(HttpConfig.APINAME.customerReportSearch)
    Observable<BaseResult<List<Report>>> getReportListData(@Query("keyWords") String keyWords, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize);

}
