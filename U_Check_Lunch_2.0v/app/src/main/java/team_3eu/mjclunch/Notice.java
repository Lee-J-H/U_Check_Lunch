package team_3eu.mjclunch;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by LeeMJ on 2018. 11. 9..
 */

public class Notice {
    public static void Noti(final Context context) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {

            Intent intent = new Intent();

            BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher);
            Bitmap bitmap = bitmapDrawable.getBitmap();

            PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext()).
                    setLargeIcon(bitmap)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis()).
                            setShowWhen(true).
                            setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle("뛰어!!")
                    .setContentText("지각하기 10분 전!!!")
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                    //.setFullScreenIntent(pendingIntent, true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            String Noti_Channel_ID = "Noti";
            String Noti_Channel_Group_ID = "Noti_Group";

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(Noti_Channel_ID, Noti_Channel_Group_ID, importance);


            notificationManager.createNotificationChannel(notificationChannel);


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), Noti_Channel_ID)
                    .setLargeIcon(null).setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setShowWhen(true)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentTitle("뛰어!!")
                    .setContentText("지각하기 10분 전!!!");

            notificationManager.notify(0, builder.build());
        }
    }
}
