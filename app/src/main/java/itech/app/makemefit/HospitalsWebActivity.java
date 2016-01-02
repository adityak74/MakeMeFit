package itech.app.makemefit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

public class HospitalsWebActivity extends ActionBarActivity {


    private WebView hosp_wv;
    private ProgressDialog pd;
    private LocationManager mgr;
    private GPSTracker gps;
    private LatLng MY_CUR_POS;
    private GoogleMap googleMap;
    private String LOCALITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals_web);

        hosp_wv = (WebView) findViewById(R.id.hosp_wv);

        hosp_wv.setWebChromeClient(new Web_Client());
        hosp_wv.getSettings().setJavaScriptEnabled(true);
        hosp_wv.getSettings().setGeolocationEnabled(true);
        //hosp_wv.getSettings().setGeolocationDatabasePath(getFilesDir().getPath());
        hosp_wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        hosp_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                    view.reload();
                    return true;
                }
                if (url.startsWith("intent://")) {
                    try {
                        Context context = view.getContext();
                        Intent intent = new Intent().parseUri(url, Intent.URI_INTENT_SCHEME);

                        if (intent != null) {
                            view.stopLoading();

                            PackageManager packageManager = context.getPackageManager();
                            ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                            if (info != null) {
                                context.startActivity(intent);
                            } else {
                                String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                                view.loadUrl(fallbackUrl);

                                // or call external broswer
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
//                    context.startActivity(browserIntent);
                            }

                            return true;
                        }
                    } catch (URISyntaxException e) {

                        Log.e("ERR", "Can't resolve intent://", e);

                    }
                    return true;
                }
                    view.loadUrl(url);
                    return true;
                }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!pd.isShowing()) {
                    pd.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
        });

        pd= new ProgressDialog(HospitalsWebActivity.this);
        pd.setIndeterminate(true);
        pd.setCancelable(true);

        //Get user city
        gps = new GPSTracker(HospitalsWebActivity.this);

        if(gps.canGetLocation()){

            try {
                pd.setMessage("Loading...");
                if(!pd.isShowing())
                    pd.show();
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                MY_CUR_POS = new LatLng(latitude,longitude);
                Geocoder gcd = new Geocoder(HospitalsWebActivity.this, Locale.getDefault());
                List<Address> addresses = gcd.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {
                    LOCALITY = addresses.get(0).getLocality();
                    String city = addresses.get(0).getLocality();
                    if(pd.isShowing())
                        pd.dismiss();
                    city = city.toLowerCase();
                    hosp_wv.loadUrl("https://www.google.co.in/search?ie=UTF-8&source=android-browser&q=" + city + "+hospitals");
                    pd.setMessage("Loading Hospitals...");
                    if(!pd.isShowing())
                        pd.show();
                }
            } catch (Exception ex) {
                Log.d("ERR", "There is an error parsing.");
            }
        }else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(HospitalsWebActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("GPS is settings");

            // Setting Dialog Message
            alertDialog
                    .setMessage("GPS is not enabled. Do you want to go to settings menu?");

            // On pressing Settings button
            alertDialog.setPositiveButton("Settings",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                             startActivity(intent);
                        }
                    });

            // on pressing cancel button
            alertDialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    });

            // Showing Alert Message
            alertDialog.show();
        }





    }

    public class Web_Client extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            if(newProgress==100){
                pd.dismiss();
            }

            super.onProgressChanged(view, newProgress);
        }

        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
        }


    }

    @Override
    public void onBackPressed() {
        // Pop the browser back stack or exit the activity
        if (hosp_wv.canGoBack()) {
            hosp_wv.goBack();
        }
        else {
            super.onBackPressed();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospitals_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
