package com.example.image_fetch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ParkingOwnerDetailspageadmin extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_owner_detailspageadmin);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        webView = (WebView)findViewById(R.id.myWebView);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipelayout);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://student-877fe.web.app/parkingowner.html");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnection();
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading....");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }

        });


    }


    public void checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobilenetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnected())
        {
            webView.loadUrl("https://student-877fe.web.app/parkingowner.html");
            webView.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
        else if (mobilenetwork.isConnected())
        {
            webView.loadUrl("https://student-877fe.web.app/parkingowner.html");
            webView.setVisibility(View.VISIBLE);
//            relativeLayout.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
        else {
            webView.setVisibility(View.GONE);
//            relativeLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
