package at.technikumwien.lecture6.data.model;

import com.google.auto.value.AutoValue;

import java.util.Date;

@AutoValue
public abstract class Event {
    public abstract String title();
    public abstract Date date();

    public static Event create(String title, Date date) {
        return new AutoValue_Event(title, date);
    }
}
