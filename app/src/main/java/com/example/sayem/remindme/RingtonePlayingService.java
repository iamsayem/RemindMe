package com.example.sayem.remindme;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Sayem on 2/15/2016.
 */
public class RingtonePlayingService extends Service {
    private static Ringtone ringtone;

    public RingtonePlayingService(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Uri ringtoneUri = Uri.parse(intent.getExtras().getString("ringtone_uri"));
        ringtone = RingtoneManager.getRingtone(getBaseContext(), ringtoneUri);
        if (!ringtone.isPlaying()){
            ringtone.play();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ringtone.isPlaying()){
            ringtone.stop();
        } else {
            ringtone.stop();
        }

    }
}
