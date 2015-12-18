package itech.app.makemefit;

/**
 * Created by aditya on 12/14/15.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHandler {

    public static final String CONFIGS = "AppConfig" ;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String val;

    SharedPrefHandler(Context ctx){
        sharedPreferences = ctx.getSharedPreferences(CONFIGS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getSharedPreferences(String key){
        if(sharedPreferences.contains(key)){
            val = sharedPreferences.getString(key, null);
            return val;
        }else{
            return "NF";
        }

    }

    public void setSharedPreferences(String key,String value) {
        editor.putString(key,value).apply();
    }
}
