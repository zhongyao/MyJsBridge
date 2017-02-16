package com.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.webview.utils.Logger;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String data = "<html><body>You scored <b>192</b> points.</body></html>";
//    private String url = "http://a.mp.uc.cn/article.html?uc_param_str=frdnsnpfvecpntnwprdssskt&client=ucweb&wm_aid=ee2d575853334ef6a48d36fc27631279&wm_id=9deef99b98bf43ab96c7a5d26259d450&pagetype=share&btifl=100";
//        private String url = "http://www.sohu.com";
private static String url = "file:///android_asset/index.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);

        /**
         * 1、invoke the Browser application with a URL Intent
         */
//        Uri uri  = Uri.parse("https://www.baidu.com");
//        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);

        /**
         * 2、load page
         */
        webView.setWebChromeClient(new XWebChromeClient());
        webView.setWebViewClient(new XWebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
        webView.loadUrl(url);

        /**
         * 3、you can also load from an HTML string
         */
//        webView.loadData(data,"text/html",null);
    }


    private class XWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logger.d("shouldOverrideUrlLoading!!"+url);
            if (Uri.parse(url).getHost().equals("www.baidu.com")) {
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }


//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            Logger.d("shouldOverrideUrlLoading!!"+request.toString());
//            if (Uri.parse(url).getHost().equals("www.baidu.com")) {
//                // This is my web site, so do not override; let my WebView load the page
//                return false;
//            }
//            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(intent);
//            return true;
//        }
    }

    private class XWebChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }
    }
}