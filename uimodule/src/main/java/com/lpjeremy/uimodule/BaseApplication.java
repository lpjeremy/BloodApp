package com.lpjeremy.uimodule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import androidx.multidex.MultiDexApplication;

import java.util.Iterator;
import java.util.Stack;

/**
 * @desc:基础的Applicaiton
 * @date:2019/8/3 14:49
 * @auther:lp
 * @version:1.1.6 APK 构建过程
 * 1、打包（res)资源文件
 * 2、通过AIDL转化aidl接口为java接口
 * 3、将java代码编译成.class文件
 * 4、将.class文件编译成.dex文件
 * 5、所有资源、.dex文件等打包成apk文件
 * 6、对apk进行签名
 * 7、正式签名的话-还需要使用工具对apk进行对齐操作（运行时减少内存开销）
 */
public class BaseApplication extends MultiDexApplication {
    /***
     * activity的堆栈
     */
    private static Stack<Activity> activityStack = new Stack<>();
    ;

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 从堆栈中移除
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定的Activity(重载)
     */
    public void finishActivity(Class<?> cls) {
        Iterator<Activity> iterator = activityStack.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
     *
     * @param cls
     */
    public void finishOthersActivity(Class<?> cls) {
        Stack<Activity> activityList = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (!(activity.getClass().equals(cls))) {
                activityList.add(activity);
            }
        }
        for (int i = 0, size = activityList.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityList.get(i).finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 应用程序退出
     */
    @SuppressLint("MissingPermission")
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            if (context != null) {
                ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.killBackgroundProcesses(context.getPackageName());
            }
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        }
    }

}
