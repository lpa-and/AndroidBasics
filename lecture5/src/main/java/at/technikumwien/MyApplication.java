package at.technikumwien;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import at.technikumwien.lecture5.data.local.ContentResolverEventRepo;
import at.technikumwien.lecture5.data.local.EventRepo;
import at.technikumwien.lecture5.receiver.AlarmReceiver;

public class MyApplication extends Application {

    private static EventRepo eventRepo;

    @Override
    public void onCreate() {
        super.onCreate();

        eventRepo = new ContentResolverEventRepo(getContentResolver());

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        final long FIFTEEN_MINUTES = 15L*60L*1000L;

        Intent intent = new Intent(AlarmReceiver.ACTION_ALARM);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 123, intent, 0);

        am.setRepeating(
                AlarmManager.RTC,
                System.currentTimeMillis() + FIFTEEN_MINUTES,
                FIFTEEN_MINUTES,
                pendingIntent
        );

        Log.i("MyApplication", "Registered alarm");
    }

    public static EventRepo getEventRepo() {
        return eventRepo;
    }
}
