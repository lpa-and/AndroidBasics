package at.technikumwien.birthdaynotifier.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.i(BootCompletedReceiver.class.getSimpleName(), "Device boot completed");

            // Set the alarms on device boot. Since an MyApplication instance
            // is created each time the app process starts, the
            // MyApplication.setAlarms() method is called when the system
            // broadcasts the boot completed action
        }
    }
}
