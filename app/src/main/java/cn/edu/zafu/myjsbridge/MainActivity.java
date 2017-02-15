package cn.edu.zafu.myjsbridge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 参考：
 * http://blog.csdn.net/sbsujjbcy/article/details/50752595#comments
 *
 * http://coolnuanfeng.github.io/jsbridge
 */

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView mWebView = (WebView) findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new JSBridgeWebChromeClient());
        mWebView.loadUrl("file:///android_asset/index.html");

        JSBridge.register("bridge", BridgeImpl.class);

    }
}
