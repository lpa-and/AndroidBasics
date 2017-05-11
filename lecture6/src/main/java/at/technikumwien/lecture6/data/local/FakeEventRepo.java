package at.technikumwien.lecture6.data.local;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.technikumwien.lecture6.data.model.Event;

public class FakeEventRepo implements EventRepo {
    @Override
    public void findAllEventsInFuture(OnDataLoadCallback<List<Event>> callback) {
        List<Event> eventList = new ArrayList<>(3);
        eventList.add(Event.create("Geburtstag", new Date(System.currentTimeMillis()+100*24*60*60*1000)));
        eventList.add(Event.create("Meeting Arbeit", new Date(System.currentTimeMillis()+10*24*60*60*1000)));
        eventList.add(Event.create("Meeting Büro", new Date(System.currentTimeMillis()+10*60*1000)));
        callback.onDataLoaded(eventList);
    }
}
