package ca.di.exemple;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONObject;

import com.dianalytics.center.fcm.DINotificationReceiver;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by samuel on 2016-11-11.
 */

public class NotificationReceiver extends DINotificationReceiver {

    @Override
    public void onMessageReceived(Context context, RemoteMessage remoteMessage) {

        try {
            JSONObject aps = new JSONObject(remoteMessage.getData().get("aps"));

            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(aps.getString("title"))
                    .setContentText(aps.getString("body"))
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notificationBuilder.build());

        } catch (Throwable t) {
            Log.e("NotificationReceiver", "Error on notification received.");
            t.printStackTrace();
            return;
        }
    }

}
