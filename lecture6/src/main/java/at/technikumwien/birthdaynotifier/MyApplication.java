package at.technikumwien.birthdaynotifier;


import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;

import at.technikumwien.birthdaynotifier.data.local.ContactRepo;
import at.technikumwien.birthdaynotifier.data.local.ContentResolverContactRepo;

public class MyApplication extends Application {

    private static ContactRepo contactRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        contactRepo = new ContentResolverContactRepo(this);

        // TODO: Set the alarms when the app process is created, otherwise
        // the alarms would not be set until the device is rebooted
        // for the first time.


        // TODO: Setup notification channel for our birthday notifications
    }

    public static ContactRepo getContactRepo() {
        return contactRepo;
    }

    private void setAlarms() {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }
}
