package org.yash;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BasicService extends Service {
    public IBinder binder = new LocalService();
    Random random = new Random();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int generateRandomNumber() {
        return random.nextInt();
    }

    public class LocalService extends Binder {
        public BasicService getService(){
            return BasicService.this;
        }
    }
}
