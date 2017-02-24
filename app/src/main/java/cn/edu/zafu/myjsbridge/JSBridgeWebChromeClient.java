package cn.edu.zafu.myjsbridge;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * @author lizhangqu
 * @since 2016-02-27 22:08
 */
public class JSBridgeWebChromeClient extends WebChromeClient {
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.d("yao","onJsPrompt");
        result.confirm(JSBridge.callJava(view, message));
        return true;
    }

//    @Override
//    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//        Log.d("yao","onJsAlert");
//        result.confirm();
//        return true;
//    }
}
