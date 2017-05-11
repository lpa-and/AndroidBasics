package at.technikumwien.lecture6.data.local;


import java.util.List;

import at.technikumwien.lecture6.data.model.Event;

public interface EventRepo {

    void findAllEventsInFuture(OnDataLoadCallback<List<Event>> callback);

}
