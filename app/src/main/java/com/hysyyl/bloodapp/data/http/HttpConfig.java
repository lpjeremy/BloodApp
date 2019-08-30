package com.hysyyl.bloodapp.data.http;

/**
 * @desc:项目http配置类
 * @date:2019/7/13 15:37
 * @auther:lp
 * @version:1.1.6
 */
public interface HttpConfig {
    /**
     * http地址
     */
    interface URL {

        String BASE_URL = "http://106.14.179.118:9110/";//项目默认请求地址

        String USER_URL = "http://106.14.179.118:9102/";

        String ORDER_URL = "http://106.14.179.118:9103/";

        String CUSTOMER_URL = "http://106.14.179.118:9107/";


    }

    /**
     * 接口名称
     */
    interface APINAME {
        String login = "api/Login/Login";
        String sendCode = "api/User/SendLoginVerifyCode";
        String getUserInfo = "api/GetMyPersonalInfo";
        String getUserBalance = "api/QueryCustomerBalance";
        String getUserSaler = "api/GetMySalerInfo";
        String checkBindWeChat = "api/Login/IsBindWxOpenid";
        String getUserOrderList = "api/Order/GetUserOrderList";
    }

    /**
     * 其他常量配置
     */
    interface CONSTANTS {
        String HIS_BASE = "HIS_BASE";
        String HIS_USER = "HIS_USER";
        String HIS_CUSTOMER = "HIS_CUSTOMER";
        String HIS_ORDER = "HIS_ORDER";
    }
}
