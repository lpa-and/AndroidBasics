package at.technikumwien.birthdaynotifier;


import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import at.technikumwien.birthdaynotifier.data.local.ContactRepo;
import at.technikumwien.birthdaynotifier.data.local.ContentResolverContactRepo;
import at.technikumwien.birthdaynotifier.data.local.FakeContactRepo;
import at.technikumwien.birthdaynotifier.receiver.BootCompletedReceiver;
import at.technikumwien.birthdaynotifier.service.BirthdayNotifierService;

public class MyApplication extends Application {

    private static ContactRepo contactRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        contactRepo = new ContentResolverContactRepo(this);
        // Use this for testing:
        // contactRepo = new FakeContactRepo();

        // Set the alarms when the app process is created, otherwise
        // the alarms would not be set until the device is rebooted
        // for the first time.
        setAlarms();
    }

    private void setAlarms() {
        Intent serviceIntent = new Intent(this, BirthdayNotifierService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, serviceIntent, 0);

        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
        // Use this for testing:
        // am.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pendingIntent);
    }

    public static ContactRepo getContactRepo() {
        return contactRepo;
    }
}
