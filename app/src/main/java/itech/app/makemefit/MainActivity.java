package itech.app.makemefit;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    LinearLayout water,food,coffee,about;
    private SharedPrefHandler sharedPrefHandler;
    private TextView textView,textView1;
    private Typeface face,faceb;
    private CardView news_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefHandler = new SharedPrefHandler(this);

        textView = (TextView)findViewById(R.id.tvMain);
        textView1 = (TextView)findViewById(R.id.tvMainHead);
        face= Typeface.createFromAsset(getAssets(), "font/ubuntu.ttf");
        faceb= Typeface.createFromAsset(getAssets(), "font/ubuntub.ttf");
        textView.setTypeface(face);
        textView1.setTypeface(faceb);

        news_card = (CardView) findViewById(R.id.news_card);

        news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                View view = getLayoutInflater().inflate(R.layout.activity_new_alert, null);
                TextView tv = (TextView)view.findViewById(R.id.tvDeveloper);
                tv.setTypeface(face);
                alertDialog.setView(view);
                // Setting Dialog Title
                alertDialog.setTitle("What's New");

                // Setting Dialog Message
                //alertDialog.setMessage("Developed by Inwhizz IT Innovations Pvt Ltd.");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.ic_inwhizz_alert_icon);

                // Setting OK Button
                alertDialog.setButton("CLOSE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });

        if(sharedPrefHandler.getSharedPreferences("welcome").equals("NF") || sharedPrefHandler.getSharedPreferences("welcome").equals("false")){
            final AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();

            // Setting Dialog Title
            alertDialog.setTitle("Thank you for Installing...");

            View view = getLayoutInflater().inflate(R.layout.activity_message_alert, null);
            TextView tv = (TextView)view.findViewById(R.id.tvMessage);
            tv.setTypeface(face);
            alertDialog.setView(view);

            // Setting Dialog Message
            //alertDialog.setMessage("This app will help you keep fit by reminding you for drinking water and having food.Developed by Inwhizz App Labs.");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.ic_inwhizz_alert_icon);

            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            // Showing Alert Message
            alertDialog.show();
            MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.thankyou);
            mPlayer.start();
            sharedPrefHandler.setSharedPreferences("welcome","done");
        }

        if(sharedPrefHandler.getSharedPreferences("food_custom").equals("NF") || sharedPrefHandler.getSharedPreferences("food_custom").equals("false")){
            sharedPrefHandler.setSharedPreferences("breakfast_time",String.valueOf(8));
            sharedPrefHandler.setSharedPreferences("lunch_time",String.valueOf(13));
            sharedPrefHandler.setSharedPreferences("dinner_time",String.valueOf(20));
            sharedPrefHandler.setSharedPreferences("food_custom","false");
        }

        if(sharedPrefHandler.getSharedPreferences("night_mode").equals("NF")){
            sharedPrefHandler.setSharedPreferences("night_mode","false");
        }

        if(sharedPrefHandler.getSharedPreferences("water").equals("NF")){
            sharedPrefHandler.setSharedPreferences("water","true");
        }

        if(sharedPrefHandler.getSharedPreferences("food").equals("NF")){
            sharedPrefHandler.setSharedPreferences("food","true");
        }

        if(sharedPrefHandler.getSharedPreferences("m_med").equals("NF")){
            sharedPrefHandler.setSharedPreferences("m_med","false");
        }

        if(sharedPrefHandler.getSharedPreferences("n_med").equals("NF")){
            sharedPrefHandler.setSharedPreferences("n_med","false");
        }

        if(sharedPrefHandler.getSharedPreferences("e_med").equals("NF")){
            sharedPrefHandler.setSharedPreferences("e_med","false");
        }

        taskSetter();

        water= (LinearLayout)findViewById(R.id.water);
        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent water = new Intent(MainActivity.this, WaterActivity.class);
                startActivity(water);
            }
        });

        food= (LinearLayout)findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent food = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(food);
            }
        });

        coffee= (LinearLayout)findViewById(R.id.coffee);
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent coffee = new Intent(MainActivity.this,Feedback.class);
                startActivity(coffee);
                /*
                final AlertDialog alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();

                // Setting Dialog Title
                alertDialog.setTitle("Coming Soon!");

                // Setting Dialog Message
                alertDialog.setMessage("Coffee! Yes! and we are working hard to bring it to you.");

                // Setting Icon to Dialog
                alertDialog.setIcon(R.mipmap.ic_launcher);

                // Setting OK Button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed
                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                */

            }
        });

        about = (LinearLayout)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent med = new Intent(MainActivity.this,MedActivity.class);
                startActivity(med);
            }
        });


    }

    public void taskSetter(){
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, TaskBroadcastReceiver.class);
        PendingIntent pintent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, 0); // For 1 PM
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Log.d("SSS",String.valueOf(cal.getTimeInMillis()));

        // schedule for every 30 seconds
        //alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pintent);
        //alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pintent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 3600 * 1000, pintent);

        //Toast.makeText(this,"Food Service Set",Toast.LENGTH_SHORT).show();
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

        if(id == R.id.menu_about){

            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                        View view = getLayoutInflater().inflate(R.layout.activity_about_alert, null);
                                        TextView tv = (TextView)view.findViewById(R.id.tvDeveloper);
                                        tv.setTypeface(face);
                                        alertDialog.setView(view);
                                        // Setting Dialog Title
                                        alertDialog.setTitle("About MakeMeFit");

                                        // Setting Dialog Message
                                        //alertDialog.setMessage("Developed by Inwhizz IT Innovations Pvt Ltd.");

                                        // Setting Icon to Dialog
                                        alertDialog.setIcon(R.drawable.ic_inwhizz_alert_icon);

                                        // Setting OK Button
                                        alertDialog.setButton("CLOSE", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Write your code here to execute after dialog closed
                                                //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                                                alertDialog.dismiss();
                                            }
                                        });

                                        // Showing Alert Message
                                        alertDialog.show();
        }

        if( id == R.id.menu_preferences ){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Make Me Fit - a human voice notification based water and food reminder app.Download now at https://play.google.com/store/apps/details?id=itech.app.makemefit");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        if(id == R.id.menu_settings){
            Intent settings = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settings);
        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
