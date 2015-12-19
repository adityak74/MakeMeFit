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
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FoodActivity extends ActionBarActivity {

    private Switch mySwitch;
    private Context ctx = this;
    private SharedPrefHandler sharedPrefHandler;
    private WebView webView;
    private TextView textView;
    private Button customizeFoodButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mySwitch = (Switch) findViewById(R.id.foodSwitch);
        sharedPrefHandler = new SharedPrefHandler(this);
        textView = (TextView)findViewById(R.id.tvFood);

        Typeface face= Typeface.createFromAsset(getAssets(), "font/ubuntu.ttf");
        textView.setTypeface(face);
        mySwitch.setTypeface(face);

        customizeFoodButton = (Button) findViewById(R.id.customizeFoodButton);

        customizeFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(FoodActivity.this, SettingsActivity.class);
                startActivity(settings);
            }
        });


        if(sharedPrefHandler.getSharedPreferences("food").equals("true")){
            mySwitch.setChecked(true);
        }

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    sharedPrefHandler.setSharedPreferences("food", "true");
                    Toast.makeText(getApplicationContext(), "Will remind you don't worry!", Toast.LENGTH_SHORT).show();
                }else{
                    sharedPrefHandler.setSharedPreferences("food","false");
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food, menu);
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
