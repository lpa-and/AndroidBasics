package at.technikumwien.lecture5.data.local;


import java.util.List;

import at.technikumwien.lecture5.data.model.Event;

public interface EventRepo {

    void findAllEventsInFuture(OnDataLoadCallback<List<Event>> callback);

}
