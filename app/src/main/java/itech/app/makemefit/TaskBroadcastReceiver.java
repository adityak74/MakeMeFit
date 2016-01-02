package itech.app.makemefit;

/**
 * Created by aditya on 12/15/15.
 */
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class TaskBroadcastReceiver extends BroadcastReceiver {

    Uri soundUri;
    @Override
    public void onReceive(final Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message

        Time time = new Time(System.currentTimeMillis());
        int hr = time.getHours();
        int min = time.getMinutes();

        final SharedPrefHandler prefHandler = new SharedPrefHandler(arg0);
        String waterBool = prefHandler.getSharedPreferences("water");
        String foodBool = prefHandler.getSharedPreferences("food");

        int breakfast_time = Integer.parseInt(prefHandler.getSharedPreferences("breakfast_time"));
        int lunch_time = Integer.parseInt(prefHandler.getSharedPreferences("lunch_time"));
        int dinner_time = Integer.parseInt(prefHandler.getSharedPreferences("dinner_time"));

        if(prefHandler.getSharedPreferences("m_med").equals("false") && prefHandler.getSharedPreferences("n_med").equals("false") && prefHandler.getSharedPreferences("e_med").equals("false")) {

            if (prefHandler.getSharedPreferences("night_mode").equals("false")) {
                if (hr >= 7 && hr <= 22 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                }
            } else {
                if (hr >= 23 && hr <= 6 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                }
            }

            if (hr == breakfast_time && min == 0 && foodBool.equals("true")) {
                //breakfast
                MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.breakfast);
                mPlayer.start();
                generateNotification(arg0, "It's time to have breakfast.Fuel up!", null);
            }
            if (hr == lunch_time && min == 0 && foodBool.equals("true")) {
                MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.lunch);
                mPlayer.start();
                generateNotification(arg0, "It's time to have lunch.Fuel up!", null);
            }
            if (hr == dinner_time && min == 0 && foodBool.equals("true")) {
                MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.dinner);
                mPlayer.start();
                generateNotification(arg0, "It's time to have dinner.Fuel up!", null);
            }
        }else{
            if(prefHandler.getSharedPreferences("m_med").equals("true") && hr==Integer.parseInt(prefHandler.getSharedPreferences("m_med_time"))){
                if (prefHandler.getSharedPreferences("night_mode").equals("false")) {
                    if (hr >= 7 && hr <= 22 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                } else {
                    if (hr >= 23 && hr <= 6 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                }

                if (hr == breakfast_time && min == 0 && foodBool.equals("true")) {
                    //breakfast
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.breakfast);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have breakfast.Fuel up!", null);
                }
                if (hr == lunch_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.lunch);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have lunch.Fuel up!", null);
                }
                if (hr == dinner_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.dinner);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have dinner.Fuel up!", null);
                }

                //One minute delay code to generate medication
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //run morning medication

                        Time time1 = new Time(System.currentTimeMillis());
                        int min1 = time1.getMinutes();

                        if(min1==1) {
                            MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.medication);
                            mPlayer.start();
                            String tname = prefHandler.getSharedPreferences("m_med_name");
                            generateNotification(arg0, "Please take your medication : " + tname, null);
                        }
                    }
                }, 1000*60);


            }
            if(prefHandler.getSharedPreferences("n_med").equals("true") && hr==Integer.parseInt(prefHandler.getSharedPreferences("n_med_time"))){

                if (prefHandler.getSharedPreferences("night_mode").equals("false")) {
                    if (hr >= 7 && hr <= 22 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                } else {
                    if (hr >= 23 && hr <= 6 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                }

                if (hr == breakfast_time && min == 0 && foodBool.equals("true")) {
                    //breakfast
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.breakfast);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have breakfast.Fuel up!", null);
                }
                if (hr == lunch_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.lunch);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have lunch.Fuel up!", null);
                }
                if (hr == dinner_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.dinner);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have dinner.Fuel up!", null);
                }

                //One minute delay code to generate medication
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //run noon medication
                        Time time1 = new Time(System.currentTimeMillis());
                        int min1 = time1.getMinutes();

                        if(min1==1) {
                            MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.medication);
                            mPlayer.start();
                            String tname = prefHandler.getSharedPreferences("m_med_name");
                            generateNotification(arg0, "Please take your medication : " + tname, null);
                        }
                    }
                }, 1000*60);


            }
            if(prefHandler.getSharedPreferences("e_med").equals("true") && hr==Integer.parseInt(prefHandler.getSharedPreferences("e_med_time"))){

                if (prefHandler.getSharedPreferences("night_mode").equals("false")) {
                    if (hr >= 7 && hr <= 22 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                } else {
                    if (hr >= 23 && hr <= 6 && hr != breakfast_time && hr != lunch_time && hr != dinner_time && min == 0 && waterBool.equals("true")) {
                        MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.water);
                        mPlayer.start();
                        generateNotification(arg0, "It's time to drink water.Fuel up!", null);
                    }
                }

                if (hr == breakfast_time && min == 0 && foodBool.equals("true")) {
                    //breakfast
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.breakfast);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have breakfast.Fuel up!", null);
                }
                if (hr == lunch_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.lunch);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have lunch.Fuel up!", null);
                }
                if (hr == dinner_time && min == 0 && foodBool.equals("true")) {
                    MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.dinner);
                    mPlayer.start();
                    generateNotification(arg0, "It's time to have dinner.Fuel up!", null);
                }

                //One minute delay code to generate medication
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //run evening medication
                        Time time1 = new Time(System.currentTimeMillis());
                        int min1 = time1.getMinutes();
                        if(min1==1) {
                            MediaPlayer mPlayer = MediaPlayer.create(arg0, R.raw.medication);
                            mPlayer.start();
                            String tname = prefHandler.getSharedPreferences("e_med_name");
                            generateNotification(arg0, "Please take your medication : " + tname, null);
                        }
                    }
                }, 1000*60);

            }
        }

        //generateNotification(arg0,"It's time to have food.Fuel up!",null);
        Log.d("SSS", "Broadcast came");
        //Toast.makeText(arg0,"Came to me",Toast.LENGTH_LONG).show();
    }

    private void generateNotification(Context ctx,String message,Uri soundUri) {
        int icon = R.drawable.ic_inwhizz_alert_icon;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);

        String title = ctx.getString(R.string.app_name);


        if(soundUri==null){
            soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        Intent notificationIntent = new Intent(ctx, MainActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent intent =
                PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(ctx, title, message, intent);
        //notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;

        // Play default notification sound
        //notification.defaults |= Notification.DEFAULT_SOUND;

        //notification.sound = soundUri;
        notification.ledARGB = Color.RED; // Red
        notification.ledOnMS = 200;
        notification.ledOffMS = 200;

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
        notificationManager.notify(0, notification);

    }


}
