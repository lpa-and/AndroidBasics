package at.technikumwien.birthdaynotifier.service;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.List;

import at.technikumwien.birthdaynotifier.MyApplication;
import at.technikumwien.birthdaynotifier.R;
import at.technikumwien.birthdaynotifier.data.local.OnDataLoadCallback;
import at.technikumwien.birthdaynotifier.data.model.Contact;
import at.technikumwien.birthdaynotifier.ui.main.MainActivity;

public class BirthdayNotifierService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(BirthdayNotifierService.class.getSimpleName(), "Service started");
        checkForBirthdays();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(BirthdayNotifierService.class.getSimpleName(), "Service stopped");
    }

    private void checkForBirthdays() {
        MyApplication.getContactRepo().findAllWithBirthday(new OnDataLoadCallback<List<Contact>>() {
            @Override
            public void onDataLoaded(List<Contact> data) {
                for(Contact contact : data) {
                    if(contact.hasBirthday()) {
                        notifyBirthday(contact);
                    }
                }

                stopSelf();
            }

            @Override
            public void onDataLoadError(Exception exception) {
                Log.e(BirthdayNotifierService.class.getSimpleName(), "Error loading birthday contacts", exception);
                stopSelf();
            }
        });
    }

    private void notifyBirthday(Contact contact) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) contact.id(), intent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.birthday_notification_text, contact.name()))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat.from(this).notify((int) contact.id(), notification);
    }
}
