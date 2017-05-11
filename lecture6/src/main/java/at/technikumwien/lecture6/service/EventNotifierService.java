package at.technikumwien.lecture6.service;


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

import at.technikumwien.MyApplication;
import at.technikumwien.lecture6.R;
import at.technikumwien.lecture6.data.local.OnDataLoadCallback;
import at.technikumwien.lecture6.data.model.Event;
import at.technikumwien.lecture6.ui.MainActivity;

public class EventNotifierService extends Service {

    private static final int NOTIFICATION_ID = 7125;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(EventNotifierService.class.getSimpleName(), "Service started");
        checkForFutureEvents();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(EventNotifierService.class.getSimpleName(), "Service stopped");
    }

    private void checkForFutureEvents() {
        MyApplication.getEventRepo().findAllEventsInFuture(new OnDataLoadCallback<List<Event>>() {
            @Override
            public void onDataLoaded(List<Event> data) {
                onEventsLoaded(data);
                stopSelf();
            }

            @Override
            public void onDataLoadError(Exception exception) {
                Log.e(EventNotifierService.class.getSimpleName(), "Could not load events", exception);
                stopSelf();
            }
        });
    }

    private void onEventsLoaded(List<Event> eventList) {
        Log.i(EventNotifierService.class.getSimpleName(), "Processing events: " + eventList.toString());

        long inFifteenMinutes = System.currentTimeMillis()+15*60*1000;

        for(Event event : eventList) {
            if(event.date().getTime() <= inFifteenMinutes) {
                showEventNotification(event);
            }
        }
    }

    private void showEventNotification(Event event) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Event in Kürze")
                .setContentText(event.title())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_notification)
                .build();

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
    }
}
