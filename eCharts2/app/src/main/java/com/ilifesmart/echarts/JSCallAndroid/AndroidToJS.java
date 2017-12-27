package com.ilifesmart.echarts.JSCallAndroid;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by hlkhjk_ok on 2017/12/25.
 */

public class AndroidToJS extends Object {

    //定义JS调用的方法，必须加入JavascriptInterface注解.
    @JavascriptInterface
    public void hello(String mes) {
        Log.d("AndroidToJS", "message: " + mes);
        Log.d("AndroidToJS", "JS Call Android's func.");
    }
}
