package org.yash;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
        Log.d("Notification: ++++++++++++++++++", remoteMessage.getNotification().getBody());
    }

    private void showNotification(String message, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel")
                .setContentText(message)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.bell);
        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(101, builder.build());
    }
}
