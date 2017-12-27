package com.ilifesmart.echarts;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.ilifesmart.echarts.JSCallAndroid.AndroidToJS;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Aurora";

    private WebView mWebView;
    private int VERSION_INT = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activigty_echart);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    public void onclickButton(View view) {
        switch (view.getId()) {
            case R.id.comparea:
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: comparea .. ");
                        mWebView.loadUrl("javascript:comparea()");
                    }
                });
                break;
            case R.id.refresh:
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: refreh .. ");
                        mWebView.loadUrl("javascript:refresh()");
                    }
                });
                break;
            case R.id.range:
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: range .. [-10 - 40]");
                        int min = -4;
                        int mnax = 36;
                        int step = 5;
                        String str = new StringBuilder().append(min).append(",").append(mnax).append(",").append(step).toString();
                        mWebView.loadUrl("javascript:setRange(" + str + ")");
                    }
                });
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        mWebView.loadUrl("file:///android_asset/echarts/hisboxthl.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            (((ViewGroup)mWebView.getParent())).removeView(mWebView);
            // destroy需在将其从View System中移除后进行.
            mWebView.destroy();
            mWebView = null;
        }
    }

    protected String getMessage() {
        return "JS Called Android ..";
    }

}
