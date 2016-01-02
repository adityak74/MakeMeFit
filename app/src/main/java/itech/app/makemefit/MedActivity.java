package itech.app.makemefit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MedActivity extends ActionBarActivity {

    private Switch md1,md2,md3;
    private TextView md1tv,md2tv,md3tv,notetv,md1_name,md2_name,md3_name;
    private SharedPrefHandler prefHandler;
    private EditText numet,med_name_et;
    private NumberPicker np1,np2,np3;
    private Dialog d1,d2,d3;
    private CardView emergency_ambulance_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);

        prefHandler = new SharedPrefHandler(this);

        md1 = (Switch)findViewById(R.id.md1_switch);
        md2 = (Switch)findViewById(R.id.md2_switch);
        md3 = (Switch)findViewById(R.id.md3_switch);

        md1tv = (TextView) findViewById(R.id.md1_tv);
        md2tv = (TextView) findViewById(R.id.md2_tv);
        md3tv = (TextView) findViewById(R.id.md3_tv);

        md1_name = (TextView) findViewById(R.id.md1_name_tv);
        md2_name = (TextView) findViewById(R.id.md2_name_tv);
        md3_name = (TextView) findViewById(R.id.md3_name_tv);

        emergency_ambulance_call = (CardView) findViewById(R.id.emergency_call);

        if(prefHandler.getSharedPreferences("m_med").equals("true")){
            md1.setChecked(true);
            if(!prefHandler.getSharedPreferences("m_med_time").equals("12"))
            md1tv.setText("Reminder set at " + prefHandler.getSharedPreferences("m_med_time") + ":00 AM");
            else
            md1tv.setText("Reminder set at " + prefHandler.getSharedPreferences("m_med_time") + ":00 PM");
        }

        if(prefHandler.getSharedPreferences("n_med").equals("true")){
            md2.setChecked(true);
            md2tv.setText("Reminder set at " + (Integer.parseInt(prefHandler.getSharedPreferences("n_med_time"))-12) + ":00 PM");
        }

        if(prefHandler.getSharedPreferences("e_med").equals("true")){
            md3.setChecked(true);
            md3tv.setText("Reminder set at " + (Integer.parseInt(prefHandler.getSharedPreferences("e_med_time"))-12) + ":00 PM");
        }

        if(prefHandler.getSharedPreferences("m_med_name").equals("NIL")){
            md1_name.setText("Tablet name not set");
        }else{
            String tname = prefHandler.getSharedPreferences("m_med_name");
            md1_name.setText("Tablet(s) : " + tname);
        }

        if(prefHandler.getSharedPreferences("n_med_name").equals("NIL")){
            md2_name.setText("Tablet name not set");
        }else{
            String tname = prefHandler.getSharedPreferences("n_med_name");
            md2_name.setText("Tablet(s) : " + tname);
        }

        if(prefHandler.getSharedPreferences("e_med_name").equals("NIL")){
            md3_name.setText("Tablet name not set");
        }else{
            String tname = prefHandler.getSharedPreferences("e_med_name");
            md3_name.setText("Tablet(s) : " + tname);
        }



        md1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    d1 = new Dialog(MedActivity.this);
                    d1.setTitle("Select Morning Time");
                    d1.setContentView(R.layout.number_picker);
                    Button b1 = (Button) d1.findViewById(R.id.button1);
                    notetv = (TextView) d1.findViewById(R.id.np_note);
                    notetv.setText("6 AM - 12 PM");
                    med_name_et = (EditText) d1.findViewById(R.id.md_name_et);
                    //Button b2 = (Button) d1.findViewById(R.id.button2);
                    final NumberPicker np = (NumberPicker) d1.findViewById(R.id.np);
                    np.setMaxValue(12);
                    np.setMinValue(6);
                    np.setWrapSelectorWheel(true);

                    np.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),String.valueOf(np.getValue()),Toast.LENGTH_LONG).show();
                            d1.dismiss();
                        }
                    });

                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                        }
                    });
                    b1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v) {
                            prefHandler.setSharedPreferences("m_med","true");
                            prefHandler.setSharedPreferences("m_med_time", String.valueOf(np.getValue()));

                            if(med_name_et.getText().toString().trim().length() > 0){
                                prefHandler.setSharedPreferences("m_med_name",med_name_et.getText().toString());
                                md1_name.setText("Tablet(s) : " + med_name_et.getText());
                            }

                            if(np.getValue()!=12)
                            md1tv.setText("Reminder set at " + String.valueOf(np.getValue()) + ":00 AM");
                            else
                            md1tv.setText("Reminder set at " + String.valueOf(np.getValue()) + ":00 PM");
                            d1.dismiss();
                        }
                    });

                    d1.show();
                }else{
                    prefHandler.setSharedPreferences("m_med", "false");
                    prefHandler.setSharedPreferences("m_med_name","NIL");
                    md1tv.setText("Reminder not set");
                    md1_name.setText("Tablet name not set");
                }
            }
        });

        md2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    d2 = new Dialog(MedActivity.this);
                    d2.setTitle("Select Noon Time");
                    d2.setContentView(R.layout.number_picker);
                    Button b1 = (Button) d2.findViewById(R.id.button1);
                    notetv = (TextView) d2.findViewById(R.id.np_note);
                    notetv.setText("1 - 6 PM");
                    med_name_et = (EditText) d1.findViewById(R.id.md_name_et);
                    //Button b2 = (Button) d1.findViewById(R.id.button2);
                    final NumberPicker np = (NumberPicker) d2.findViewById(R.id.np);
                    np.setMaxValue(6);
                    np.setMinValue(1);
                    np.setWrapSelectorWheel(false);
                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                        }
                    });
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prefHandler.setSharedPreferences("n_med","true");
                            prefHandler.setSharedPreferences("n_med_time", String.valueOf(np.getValue()+12));

                            if(med_name_et.getText().toString().trim().length() > 0){
                                prefHandler.setSharedPreferences("n_med_name",med_name_et.getText().toString());
                                md2_name.setText("Tablet(s) : " + med_name_et.getText());
                            }

                            Log.d("ttt",prefHandler.getSharedPreferences("n_med_time"));
                            md2tv.setText("Reminder set at " + String.valueOf(np.getValue()) + ":00 PM");
                            d2.dismiss();
                        }
                    });

                    d2.show();
                }else{
                    prefHandler.setSharedPreferences("n_med", "false");
                    prefHandler.setSharedPreferences("n_med_name","NIL");
                    md2tv.setText("Reminder not set");
                    md2_name.setText("Tablet name not set");
                }
            }
        });

        md3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    d3 = new Dialog(MedActivity.this);
                    d3.setTitle("Select Evening Time");
                    d3.setContentView(R.layout.number_picker);
                    Button b1 = (Button) d3.findViewById(R.id.button1);
                    notetv = (TextView) d3.findViewById(R.id.np_note);
                    notetv.setText("7 - 11 PM");
                    med_name_et = (EditText) d1.findViewById(R.id.md_name_et);
                    //Button b2 = (Button) d1.findViewById(R.id.button2);
                    final NumberPicker np = (NumberPicker) d3.findViewById(R.id.np);
                    np.setMaxValue(11);
                    np.setMinValue(7);
                    np.setWrapSelectorWheel(false);
                    np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                        @Override
                        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                        }
                    });
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prefHandler.setSharedPreferences("e_med","true");
                            prefHandler.setSharedPreferences("e_med_time", String.valueOf(np.getValue()+12));

                            if(med_name_et.getText().toString().trim().length() > 0){
                                prefHandler.setSharedPreferences("e_med_name",med_name_et.getText().toString());
                                md3_name.setText("Tablet(s) : " + med_name_et.getText());
                            }

                            Log.d("ttt", prefHandler.getSharedPreferences("e_med_time"));
                            md3tv.setText("Reminder set at " + String.valueOf(np.getValue()) + ":00 PM");
                            d3.dismiss();
                        }
                    });

                    d3.show();
                }else{
                    prefHandler.setSharedPreferences("e_med", "false");
                    prefHandler.setSharedPreferences("e_med_name","NIL");
                    md3tv.setText("Reminder not set");
                    md3_name.setText("Tablet name not set");
                }
            }
        });


        emergency_ambulance_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MedActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle("Call Ambulance");

                // Setting Dialog Message
                alertDialog
                        .setMessage("Do you really want to call ambulance?");

                // On pressing Settings button
                alertDialog.setPositiveButton("Call",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:+91108"));
                                startActivity(callIntent);
                            }
                        });

                // on pressing cancel button
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // Showing Alert Message
                alertDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_med, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
