package at.technikumwien.birthdaynotifier;

import android.app.Application;

import at.technikumwien.birthdaynotifier.data.local.ContactRepo;
import at.technikumwien.birthdaynotifier.data.local.ContentProviderContactRepo;

public class BirthdayNotifierApplication extends Application {

    private ContactRepo contactRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        this.contactRepo = new ContentProviderContactRepo(this);
    }

    public ContactRepo getContactRepo() {
        return contactRepo;
    }
}
