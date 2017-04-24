package at.technikumwien.lecture5.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("BootReceiver", "Device booted");
        } else {
            Log.i("BootReceiver", "Something else :(");
        }
    }

}
