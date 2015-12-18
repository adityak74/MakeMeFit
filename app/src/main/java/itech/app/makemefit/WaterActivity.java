package itech.app.makemefit;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class WaterActivity extends ActionBarActivity {

    private Switch mySwitch;
    private Context ctx = this;
    private SharedPrefHandler sharedPrefHandler;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mySwitch = (Switch) findViewById(R.id.waterSwitch);
        sharedPrefHandler = new SharedPrefHandler(this);

        textView = (TextView)findViewById(R.id.tvWater);
        Typeface face= Typeface.createFromAsset(getAssets(), "font/ubuntu.ttf");
        textView.setTypeface(face);
        mySwitch.setTypeface(face);

        if(sharedPrefHandler.getSharedPreferences("water").equals("true")){
            mySwitch.setChecked(true);
        }


        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    sharedPrefHandler.setSharedPreferences("water","true");
                    Toast.makeText(getApplicationContext(), "Will remind you don't worry!", Toast.LENGTH_SHORT).show();
                }else{
                    sharedPrefHandler.setSharedPreferences("water","false");
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_water, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
