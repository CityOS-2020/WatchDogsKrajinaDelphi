package com.hackaton.adim.watchdogskrajina;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //incijalizacija i osnovne postavke web browsera
        final WebView web= (WebView) findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        web.loadUrl("http://watchdogskrajina.herokuapp.com/mobile/index.html");


        //inicijalizacija i osnovne postavke za button centriraj
        final Button btn_center=(Button)findViewById(R.id.button);
        btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kod za web browser
                //web.evaluateJavascript("alert('test')");
                web.loadUrl("javascript:(function(){ alert(4);})()");
              /*  web.evaluateJavascript("(function() { return null; })();", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.d("LogName", s); // Prints the string 'null' NOT Java null
                    }
                });

                web.evaluateJavascript("(function('test') { })();", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        Log.d("LogName", s); //s is Java null
                    }
                });*/
                Toast.makeText(getApplicationContext(),"Poruka",Toast.LENGTH_LONG).show();
            }});

        // inicijalizacija i osnovne postavke switch button-a
        final Switch swc_info=(Switch)findViewById(R.id.switch1);
        swc_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swc_info.isChecked())
                {
                   Toast.makeText(MainActivity.this,"Transport mapa",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Info mapa", Toast.LENGTH_SHORT).show();
                }
            }});

        if(swc_info.isChecked()){
            swc_info.setText("Info Map");
        }
        else {
            swc_info.setText("Transport map");
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
