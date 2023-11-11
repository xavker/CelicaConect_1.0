package com.example.celicaconect_10.Navegador.Menu.ConsultarLuz;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.celicaconect_10.NoConexion.Probar_conexion;
import com.example.celicaconect_10.R;


public class Luz extends Fragment {

    Probar_conexion probar_conexion;
    private WebView webView;
    ProgressDialog progressBar;
    String TAG ="TAG";
    private View view;
    private final String urlWeb="http://186.178.204.52/confac/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_luz, container, false);

        webView=view.findViewById(R.id.webView_map);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        progressBar=new ProgressDialog(getContext(),R.style.AppCompatAlertDialogStyle);


        if(probar_conexion.isConnected(getContext())) {
            progressBar = ProgressDialog.show(getContext(), "Conectando al servidor", "Loading...");
            Toast.makeText(getActivity(),"Conectando al sevidor...",Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.i(TAG, "Processing webview url click...");
                    view.loadUrl(url);
                    return true;
                }

                public void onPageFinished(WebView view, String url) {
                    Log.i(TAG, "Finished loading URL: " +url);
                    if (progressBar.isShowing()) {
                        progressBar.dismiss();
                    }
                }

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.e(TAG, "Error: " + description);
                    Toast.makeText(getContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(description);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    alertDialog.show();
                }
            });
            webView.loadUrl(urlWeb);

        }else {
            view = inflater.inflate(R.layout.fragment_noconexion, container, false);
            Toast.makeText(getActivity(),"Sin Internet",Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
