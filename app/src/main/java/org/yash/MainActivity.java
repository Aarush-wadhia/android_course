package org.yash;

import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myReceiver = new MyReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null!= myReceiver) {
            unregisterReceiver(myReceiver);
        }
    }
}
