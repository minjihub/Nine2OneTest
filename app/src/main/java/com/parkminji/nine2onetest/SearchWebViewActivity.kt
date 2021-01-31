package com.parkminji.nine2onetest

import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*

class SearchWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var url = intent.getStringExtra("url")
        if(url != null && url.startsWith("http://")){
            url = url.replaceFirst("http://","https://")
        }

        if(url == null || url.isEmpty()){
            finish()
        }

        web_view.apply {
            webViewClient = object: WebViewClient(){
                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Log.e("onReceived_오류", error?.description.toString())
                    }
                }
                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    Log.e("ssl_오류", error?.primaryError.toString())
                    handler?.proceed()
                }
            }
            settings.javaScriptEnabled = true
            loadUrl(url!!)
        }
    }
}