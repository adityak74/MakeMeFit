package itech.app.makemefit;

import android.app.TimePickerDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends ActionBarActivity {


    private Button breakfast,lunch,dinner;
    private SharedPrefHandler sharedPrefHandler;
    private Switch customFoodSwitch,nightModeSwitch,allNotificationsToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sharedPrefHandler = new SharedPrefHandler(this);
        customFoodSwitch = (Switch)findViewById(R.id.switchSetFoodTimings);
        nightModeSwitch = (Switch)findViewById(R.id.nightModeSwitch);
        allNotificationsToggle = (Switch)findViewById(R.id.allNotificationsToggle);

        if(sharedPrefHandler.getSharedPreferences("water").equals("true")||sharedPrefHandler.getSharedPreferences("food").equals("true")){
            allNotificationsToggle.setChecked(true);
        }

        if(sharedPrefHandler.getSharedPreferences("night_mode").equals("true")){
            nightModeSwitch.setChecked(true);
        }

        if(sharedPrefHandler.getSharedPreferences("food_custom").equals("true")){
            customFoodSwitch.setChecked(true);
        }

        allNotificationsToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefHandler.setSharedPreferences("food","true");
                    sharedPrefHandler.setSharedPreferences("water","true");
                }else{
                    sharedPrefHandler.setSharedPreferences("food","false");
                    sharedPrefHandler.setSharedPreferences("water","false");
                    sharedPrefHandler.setSharedPreferences("food_custom","false");
                    nightModeSwitch.setChecked(false);
                    customFoodSwitch.setChecked(false);
                }
            }
        });

        customFoodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefHandler.setSharedPreferences("food_custom", "true");
                    allNotificationsToggle.setChecked(true);
                    sharedPrefHandler.setSharedPreferences("food","true");
                }else{
                    sharedPrefHandler.setSharedPreferences("breakfast_time",String.valueOf(8));
                    sharedPrefHandler.setSharedPreferences("lunch_time",String.valueOf(13));
                    sharedPrefHandler.setSharedPreferences("dinner_time",String.valueOf(20));
                    sharedPrefHandler.setSharedPreferences("food_custom","false");
                }
            }
        });

        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPrefHandler.setSharedPreferences("night_mode","true");
                    allNotificationsToggle.setChecked(true);
                    sharedPrefHandler.setSharedPreferences("water","true");
                }else{
                    sharedPrefHandler.setSharedPreferences("night_mode","false");
                }
            }
        });

        breakfast = (Button) findViewById(R.id.breakfastBt);

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new CustomTimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(), String.valueOf(hourOfDay), Toast.LENGTH_LONG).show();
                        customFoodSwitch.setChecked(true);
                        sharedPrefHandler.setSharedPreferences("food_custom", "true");
                        sharedPrefHandler.setSharedPreferences("breakfast_time",String.valueOf(hourOfDay));
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Breakfast Time");
                mTimePicker.show();
            }
        });

        lunch = (Button) findViewById(R.id.lunchBt);

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new CustomTimePickerDialog( SettingsActivity.this , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(),String.valueOf(hourOfDay),Toast.LENGTH_LONG).show();
                        customFoodSwitch.setChecked(true);
                        sharedPrefHandler.setSharedPreferences("food_custom", "true");
                        sharedPrefHandler.setSharedPreferences("lunch_time",String.valueOf(hourOfDay));
                    }
                },hour,minute,true);
                mTimePicker.setTitle("Select Lunch Time");
                mTimePicker.show();
            }
        });

        dinner = (Button) findViewById(R.id.dinnerBt);

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new CustomTimePickerDialog( SettingsActivity.this , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getApplicationContext(),String.valueOf(hourOfDay),Toast.LENGTH_LONG).show();
                        customFoodSwitch.setChecked(true);
                        sharedPrefHandler.setSharedPreferences("food_custom", "true");
                        sharedPrefHandler.setSharedPreferences("lunch_time",String.valueOf(hourOfDay));
                    }
                },hour,minute,true);
                mTimePicker.setTitle("Select Dinner Time");
                mTimePicker.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
