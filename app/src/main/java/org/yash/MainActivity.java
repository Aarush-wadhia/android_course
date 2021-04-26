package org.yash;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotifications();
            }
        });
    }

    private void sendNotifications() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel")
                .setContentTitle("Notification")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("This is my first notification");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, builder.build());
    }
}
