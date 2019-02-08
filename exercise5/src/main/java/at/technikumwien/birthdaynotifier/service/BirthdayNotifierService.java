package at.technikumwien.birthdaynotifier.service;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;

import java.util.List;

import at.technikumwien.birthdaynotifier.MyApplication;
import at.technikumwien.birthdaynotifier.R;
import at.technikumwien.birthdaynotifier.data.local.OnDataLoadCallback;
import at.technikumwien.birthdaynotifier.data.model.Contact;
import at.technikumwien.birthdaynotifier.ui.main.MainActivity;

public class BirthdayNotifierService extends Service {

    private static String NOTIFICATION_CHANNEL_ID = "birthdays";

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

        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.birthday_notification_text, contact.name()))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManagerCompat.from(this).notify((int) contact.id(), notification);
    }

    public static void createNotificationChannel(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            CharSequence name = context.getString(R.string.notification_channel_birthday_name);
            String description = context.getString(R.string.notification_channel_birthday_description);
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
