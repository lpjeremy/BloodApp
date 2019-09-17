package com.hysyyl.bloodapp.data.http.api;

import com.hysyyl.bloodapp.model.Report;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

import java.util.List;

public interface ReportApi {
    void loadReportData(String keyWords, int pageIndex, int pageSize, HttpRequestCallBackKT<List<Report>> callBack);
}
