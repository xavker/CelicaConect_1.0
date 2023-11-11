package com.example.celicaconect_10.Navegador.Menu.MultasTransito;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Mywebviewclient extends WebViewClient {


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(Uri.parse(url).getHost().endsWith("google.com")){
            return false;
        }
        Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        view.getContext().startActivity(i);
        return true;
    }
}
