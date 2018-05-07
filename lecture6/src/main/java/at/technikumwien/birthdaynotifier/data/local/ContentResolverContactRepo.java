package at.technikumwien.birthdaynotifier.data.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.technikumwien.birthdaynotifier.data.model.Contact;
import at.technikumwien.birthdaynotifier.util.Utils;

/**
 * A repository that uses the ContentResolver to fetch Contacts.
 */
public class ContentResolverContactRepo implements ContactRepo {

    private final ContentResolver contentResolver;

    public ContentResolverContactRepo(Context context) {
        this.contentResolver = context.getContentResolver();
    }

    /**
     * Finds all contacts which have a birthday set.
     *
     * @param callback The callback to be invoked on data load events
     */
    @Override
    public void findAllWithBirthday(@NonNull final OnDataLoadCallback<List<Contact>> callback) {
        // We use AsyncTask because data loading should always be done on a background thread
        new AsyncTask<Void, Void, List<Contact>>() {

            private Exception exception = null;

            @Override
            protected List<Contact> doInBackground(Void... params) {
                List<Contact> contactList = new ArrayList<>();
                Uri uri = ContactsContract.Data.CONTENT_URI;

                // The columns/data parts we want to fetch
                String[] projection = new String[] {
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Event.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Event.START_DATE
                };

                // Selection criteria for the data we want to fetch
                String where = ContactsContract.Data.MIMETYPE + "= ? AND "
                        + ContactsContract.CommonDataKinds.Event.TYPE + "="
                        + ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY;

                // Arguments for the selection criteria (replaces the ? parts)
                String[] selectionArgs = new String[] { ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE };

                // The sort order of the data. Passing in null returns data in the default order
                String sortOrder = null;

                // Query the content via the content resolver
                try(Cursor cursor = contentResolver.query(uri, projection, where, selectionArgs, sortOrder)) {
                    // Check whether the cursor is not null
                    if (cursor != null) {
                        // Get the column indices of our columns for fetching them
                        int nameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                        int idColumn = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.CONTACT_ID);
                        int birthday = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE);

                        // Fetch the next item, if there is one left
                        while(cursor.moveToNext()) {
                            long id = cursor.getLong(idColumn);
                            String name = cursor.getString(nameColumn);
                            Date date = Utils.parseDate(cursor.getString(birthday));

                            if(name != null && date != null) {
                                contactList.add(Contact.create(id, name, date));
                            } else {
                                Log.i("ContactRepo", "Could not parse birthday of contact \"" + name + "\" (ID: " + id + ")");
                            }
                        }
                    }

                    return contactList;
                } catch(Exception e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Contact> contactList) {
                if(exception != null) {
                    callback.onDataLoadError(exception);
                } else {
                    callback.onDataLoaded(contactList);
                }
            }
        }.execute();
        // Don't forget to call execute()!
    }
}
