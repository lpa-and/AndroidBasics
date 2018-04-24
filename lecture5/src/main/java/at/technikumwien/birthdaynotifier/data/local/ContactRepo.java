package at.technikumwien.birthdaynotifier.data.local;

import java.util.List;

import at.technikumwien.birthdaynotifier.data.model.Contact;

public interface ContactRepo {

    void findAllContactsWithBirthdays(OnDataLoadCallback<List<Contact>> callback);

}
