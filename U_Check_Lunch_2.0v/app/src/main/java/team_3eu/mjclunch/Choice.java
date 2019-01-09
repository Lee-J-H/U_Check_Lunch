package team_3eu.mjclunch;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by LeeMJ on 2018. 11. 9..
 */

public class Choice extends AppCompatActivity {
    public static void writeChoice(Context context, String a) {
        SharedPreferences mode = context.getSharedPreferences("mode", MODE_PRIVATE);
        SharedPreferences.Editor editor = mode.edit();
        if (a.equals("Yes")) {
            editor.putString("mode", "simple");

        }
        else {
            editor.putString("mode", "normal");

        }
        editor.commit();
    }

    public static String readChoice(Context context) {
        SharedPreferences mode = context.getSharedPreferences("mode", MODE_PRIVATE);
        SharedPreferences.Editor editor = mode.edit();
        String choice = mode.getString("mode", "");
        return choice;
    }//http://devstory.ibksplatform.com/2017/12/android-sharedpreferences.html

}
