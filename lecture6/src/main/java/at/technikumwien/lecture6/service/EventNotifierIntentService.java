package at.technikumwien.lecture6.service;


import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class EventNotifierIntentService extends IntentService {

    public EventNotifierIntentService() {
        super("EventNotifierIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Data modifications, data loading, whatever …
        // This is called in a background thread
    }
}
