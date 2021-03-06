package com.umarbhutta.xlightcompanion.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.umarbhutta.xlightcompanion.R;

/**
 * Created by Administrator on 2017/3/5.
 */

public class HelpFragment extends Fragment implements View.OnClickListener {

    private  WebView webView;
    private ImageButton ib_back,ib_refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        webView = (WebView) view.findViewById(R.id.helpWebview);
        ib_back = (ImageButton) view.findViewById(R.id.ib_back);
        ib_back.setOnClickListener(this);
        ib_refresh = (ImageButton) view.findViewById(R.id.ib_refresh);
        ib_refresh.setOnClickListener(this);
        //TODO webview加载数据
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl("http://baidu.com");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成

                } else {
                    // 加载中

                }

            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ib_back:
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面
                }
                break;
            case R.id.ib_refresh:
                webView.reload();//刷新
                break;
        }
    }
}
