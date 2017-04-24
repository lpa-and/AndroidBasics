package at.technikumwien.lecture5.data.local;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.technikumwien.lecture5.data.model.Event;

public class ContentResolverEventRepo implements EventRepo {

    private final ContentResolver contentResolver;

    public ContentResolverEventRepo(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void findAllEventsInFuture(final OnDataLoadCallback<List<Event>> callback) {
        new AsyncTask<Void, Void, List<Event>>() {

            private Exception exception = null;

            @Override
            protected List<Event> doInBackground(Void... params) {
                List<Event> eventList = new ArrayList<>();

                Uri uri = CalendarContract.Events.CONTENT_URI;

                // The columns/data parts we want to fetch
                String[] projection = new String[] {
                        CalendarContract.Events.TITLE,
                        CalendarContract.Events.DTSTART
                };

                // Selection criteria for the data we want to fetch
                String where = CalendarContract.Events.DTSTART + " >= " + System.currentTimeMillis();

                // Arguments for the selection criteria (replaces the ? parts)
                String[] selectionArgs = null;

                // The sort order of the data. Passing in null returns data in the default order
                String sortOrder = null;

                // Query the content via the content resolver
                //noinspection MissingPermission
                try(Cursor cursor = contentResolver.query(uri, projection, where, selectionArgs, sortOrder)) {
                    // Check whether the cursor is not null
                    if (cursor != null) {
                        // Get the column indices of our columns for fetching them
                        int titleColumn = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                        int dateColumn = cursor.getColumnIndex(CalendarContract.Events.DTSTART);

                        // Fetch the next item, if there is one left
                        while(cursor.moveToNext()) {
                            String title = cursor.getString(titleColumn);
                            long dateInMillis = cursor.getLong(dateColumn);
                            Date date = new Date(dateInMillis);

                            if(title != null && date != null) {
                                eventList.add(Event.create(title, date));
                            } else {
                                Log.i("EventRepo", "Could not create event");
                            }
                        }
                    }

                    return eventList;

                } catch(Exception e) {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Event> events)  {
                if (events != null) {
                    callback.onDataLoaded(events);
                } else {
                    callback.onDataLoadError(exception);
                }
            }

        }.execute();
    }

}
