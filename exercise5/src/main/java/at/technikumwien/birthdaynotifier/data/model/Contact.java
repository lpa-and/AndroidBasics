package at.technikumwien.birthdaynotifier.data.model;

import com.google.auto.value.AutoValue;

import java.util.Date;

import at.technikumwien.birthdaynotifier.util.Utils;

// AutoValue creates an implementation of our abstract class,
// for easy immutable objects.
// Per default, null values are not accepted. To allow null
// values for a property, add the @Nullable annotation.
// See here for more info:
// https://github.com/google/auto/blob/master/value/userguide/index.md
@AutoValue
public abstract class Contact {

    // Instead of member variables, with AutoValue we
    // create abstract getter methods. AutoValue then
    // automatically creates a class implementing these
    // getters
    public abstract long id();
    public abstract String name();
    public abstract Date birthday();

    // Create a new Contact by using the generated class AutoValue_Contact
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
