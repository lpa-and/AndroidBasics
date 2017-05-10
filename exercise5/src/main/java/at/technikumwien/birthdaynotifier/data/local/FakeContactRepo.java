package at.technikumwien.birthdaynotifier.data.local;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.technikumwien.birthdaynotifier.data.model.Contact;

/**
 * A repository returning fake contacts
 */
public class FakeContactRepo implements ContactRepo {
    @Override
    public void findAllWithBirthday(@NonNull OnDataLoadCallback<List<Contact>> callback) {
        List<Contact> contactList = new ArrayList<>(2);
        contactList.add(Contact.create(1, "Max Mustermann", new Date()));
        contactList.add(Contact.create(2, "Maria Musterfrau", new Date(System.currentTimeMillis()-10*24*60*60*1000)));
        callback.onDataLoaded(contactList);
    }
}
