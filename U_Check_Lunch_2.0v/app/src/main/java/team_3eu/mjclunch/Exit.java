package team_3eu.mjclunch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

/**
 * Created by LeeMJ on 2018. 11. 9..
 */

public class Exit {
    public static void exitDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("종료하시겠습니까?")
                .setMessage("종료하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        context.startActivity(intent);
                        // 출처: http://gogorchg.tistory.com/entry/Android-앱을-종료-하고자-할때 [항상 초심으로]
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        // 출처: http://shstarkr.tistory.com/144 [아마그래머]
    }
}
