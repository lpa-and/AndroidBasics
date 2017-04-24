package at.technikumwien.lecture5.data;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Message {
    public abstract String title();
    public abstract String message();

    public static Message create(String title, String message) {
        return new AutoValue_Message(title, message);
    }
}
