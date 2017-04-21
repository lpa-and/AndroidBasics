package at.technikumwien.birthdaynotifier;


import android.app.Application;

import at.technikumwien.birthdaynotifier.data.local.ContactRepo;
import at.technikumwien.birthdaynotifier.data.local.ContentResolverContactRepo;

public class MyApplication extends Application {

    private static ContactRepo contactRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        contactRepo = new ContentResolverContactRepo(this);
    }

    public static ContactRepo getContactRepo() {
        return contactRepo;
    }
}
