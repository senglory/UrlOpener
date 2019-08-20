package com.camargon.taramo.urlopener;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.ComponentName;
import android.util.Log;
import android.widget.TextView;
import java.io.*;

/*
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import java.net.URI;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
*/


public class MainTorActivity extends AppCompatActivity {

    private TextView ctlDemoText;


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tor);

        onNewIntent(getIntent());

        ctlDemoText = (TextView) findViewById(R.id.txtHELLO);



        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor);
        finish();

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (type == null)
            return;

        if (!intent.hasExtra(Intent.EXTRA_STREAM))
            return;

        if (Intent.ACTION_MAIN.equals(action)) {
            Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
            openUrlInFirefox(imageUri);
        }
        else if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
            ArrayList<Parcelable> list = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
            for (Parcelable p : list) {
                openUrlInFirefox((Uri) p);
            }
        }



        ctlDemoText = (TextView) findViewById(R.id.txtHELLO);
        ctlDemoText.setText("W OR L В ");

        Intent intent2 = getIntent();
        //var act1=intent.getAction();
        Uri uri = intent2.getData();

        openUrlInFirefox(uri);

        String url = "http://jdpa.com/";


*/

    }

    private void openUrlInFirefox(Uri uri) {
        Intent intent3 = new Intent(Intent.ACTION_MAIN, null);
        intent3.addCategory(Intent.CATEGORY_LAUNCHER);
        intent3.setComponent(new ComponentName("com.cloudmosa.puffin", "com.cloudmosa.puffin.App"));
        intent3.setAction("org.mozilla.gecko.BOOKMARK");
        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent3.putExtra("args", "--url=" + uri.toString());
        intent3.setData(uri);
        startActivity(intent3);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mIntent = intent;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mIntent != null) {
            final String action = mIntent.getAction();

            ctlDemoText.setText(action);




            if ("android.intent.action.VIEW".equalsIgnoreCase(action)) {




                // Обработка старта из лончера
                Intent intent2 = getIntent();
                //var act1=intent.getAction();
                Uri uri = intent2.getData();

                ctlDemoText = (TextView) findViewById(R.id.txtHELLO);
                if (uri!=null) {
                    // the passed param has look like file:///storage/sdcard0/DCIM/3.url
                    String fileName = uri.getPath();

                    ctlDemoText.setText(fileName);

                    // read the url file
                    File file = new File(fileName);

//Read text from file
                    StringBuilder text = new StringBuilder();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            //text.append('\n');
                        }
                        br.close();
                    }
                    catch (IOException e) {
                        //You'll need to add proper error handling here
                    }
                    uri = Uri.parse(text.toString());
                    openUrlInFirefox(uri);


                }

                uri=(Uri) intent2.getParcelableExtra(Intent.EXTRA_STREAM);
                if (uri==null)
                {
                    uri = Uri.parse("http://www.sbrf.ru");
                }
                //openUrlInFirefox(uri);
            }
            mIntent = null;
        }
    }
}
