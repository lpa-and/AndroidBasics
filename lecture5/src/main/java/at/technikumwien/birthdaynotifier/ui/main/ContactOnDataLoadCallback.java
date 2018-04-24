package at.technikumwien.birthdaynotifier.ui.main;

import java.util.List;

import at.technikumwien.birthdaynotifier.data.local.OnDataLoadCallback;
import at.technikumwien.birthdaynotifier.data.model.Contact;

public class ContactOnDataLoadCallback implements OnDataLoadCallback<List<Contact>> {
    @Override
    public void onDataLoaded(List<Contact> data) {

    }

    @Override
    public void onDataLoadError(Exception exception) {

    }
}
