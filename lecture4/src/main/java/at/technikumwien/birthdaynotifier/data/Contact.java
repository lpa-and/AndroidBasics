package at.technikumwien.birthdaynotifier.data;

import com.google.auto.value.AutoValue;

import java.util.Date;

import at.technikumwien.birthdaynotifier.util.Utils;

@AutoValue
public abstract class Contact {

    public abstract long id();
    public abstract String name();
    public abstract Date birthday();

    public static Contact create(long id, String name, Date birthday) {
        return new AutoValue_Contact(id, name, birthday);
    }

    // Getters, that use our util methods to extract additional
    // useful information from our Contact model

    public String getFormattedBirthday() {
        return Utils.formatBirthday(birthday());
    }

    public boolean hasBirthday() {
        return Utils.isToday(birthday());
    }
}
