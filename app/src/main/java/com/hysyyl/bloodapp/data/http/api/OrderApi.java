package com.hysyyl.bloodapp.data.http.api;


import com.hysyyl.bloodapp.model.Order;
import com.lpjeremy.libmodule.http.callback.HttpRequestCallBackKT;

import java.util.List;

public interface OrderApi {
    void getUserOrderList(String keyWords, int orderStatus, int pageIndex, int pageSize, HttpRequestCallBackKT<List<Order>> callBack);

}
