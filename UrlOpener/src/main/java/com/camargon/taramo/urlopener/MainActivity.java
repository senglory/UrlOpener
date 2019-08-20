package com.camargon.taramo.urlopener;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.ComponentName;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.*;
import android.os.Build;
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


public class MainActivity extends AppCompatActivity {

    private TextView ctlDemoText;

    private static final int READ_REQUEST_CODE = 42;
    private static final int PERMISSION_REQUEST_CODE = 1;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        onNewIntent(getIntent());

        ctlDemoText = findViewById(R.id.txtHELLO);


        // https://stackoverflow.com/questions/33162152/storage-permission-error-in-marshmallow
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
            } else {
                requestPermission(); // Code for permission
            }
        }
        else
        {

            // Code for Below 23 API Oriented Device
            // Do next code
        }


        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    private void openUrlInFirefox(Uri uri) {

        String androidOS = Build.VERSION.RELEASE;
        Intent intentFF;

        if (androidOS.equals("6.0")) {

            ctlDemoText.setText(uri.toString());

            intentFF = new Intent(Intent.ACTION_VIEW);
            intentFF.setComponent(new ComponentName("org.mozilla.firefox","org.mozilla.firefox.App"));
            intentFF.setData (uri);
            startActivity(intentFF);
        }
        else
        {
            intentFF = new Intent(Intent.ACTION_MAIN, null);
            intentFF.addCategory(Intent.CATEGORY_LAUNCHER);
            intentFF.setComponent(new ComponentName("org.mozilla.firefox", "org.mozilla.firefox.App"));
            intentFF.setAction("org.mozilla.gecko.BOOKMARK");
            intentFF.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentFF.putExtra("args", "--url=" + uri.toString());
            intentFF.setData(uri);
            startActivity(intentFF);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
//                Log.i(TAG, "Uri: " + uri.toString());
//                showImage(uri);
            }
        }
    }


   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case READ_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }*/



    @Override
    protected void onNewIntent(Intent intent) {
        mIntent = intent;
    }

    @Override
    protected void onStart() {
        super.onStart();

String androidOS = Build.VERSION.RELEASE;
//ctlDemoText.setText(androidOS);
//        ctlDemoText.setText("--- ERFOLGREICH ---");




        if (mIntent != null) {
            mIntent.addCategory(Intent.CATEGORY_OPENABLE);
            final String actionTxt = mIntent.getAction();

//            ctlDemoText.setText(actionTxt);

            if ("android.intent.action.VIEW".equalsIgnoreCase(actionTxt)) {

                // Обработка старта из лончера
                Intent intent2 = getIntent();
                //intent2.getAction();
                Uri uri = mIntent.getData();


                ctlDemoText = findViewById(R.id.txtHELLO);
                if (uri!=null) {
                    // the passed param has look like file:///storage/sdcard0/DCIM/3.url
                    String fileName = uri.getPath();



                    // read the url file
                    File file = new File(fileName);
                    StringBuilder text = new StringBuilder();
                    BufferedReader br=null;

                    try {
                        br = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            //text.append('\n');
                        }
                        br.close();

                        String txtUri = text.toString();
                        ctlDemoText.setText(txtUri);

                        uri = Uri.parse(txtUri);
                        openUrlInFirefox(uri);
                    }
                    catch (IOException e) {
                        ctlDemoText.setText(e.getMessage());
                    }
                    finally
                    {
                        try {
                            if (br != null) {
                                br.close();
                            }
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                //uri=(Uri) intent2.getParcelableExtra(Intent.EXTRA_STREAM);
                //if (uri==null)
                //{
                //    uri = Uri.parse("http://www.sbrf.ru");
                //}
                //openUrlInFirefox(uri);
            }
            mIntent = null;
        }
    }


    public void openFF(View view) {
        Intent intentFF;

        Uri uri = Uri.parse("http://www.jdpa.com");
        intentFF = new Intent(Intent.ACTION_VIEW);
        intentFF.setComponent(new ComponentName("org.mozilla.firefox","org.mozilla.firefox.App"));
        intentFF.setData (uri);
        startActivity(intentFF);
    }

}
