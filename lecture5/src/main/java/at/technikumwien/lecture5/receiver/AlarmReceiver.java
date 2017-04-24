package at.technikumwien.lecture5.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    public static String ACTION_ALARM = "at.technikumwien.lecture5.ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver", "Received alarm with action " + intent.getAction());
    }

}
