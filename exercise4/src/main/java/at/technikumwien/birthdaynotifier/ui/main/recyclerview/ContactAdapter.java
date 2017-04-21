package at.technikumwien.birthdaynotifier.ui.main.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import at.technikumwien.birthdaynotifier.R;
import at.technikumwien.birthdaynotifier.data.model.Contact;

/**
 * Please read the {@link RecyclerView.Adapter} java docs.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    // At first, we have no items, therefore we can set an empty list
    private List<Contact> contactList = Collections.emptyList();

    // In this method, a new list of contacts is set. After
    // we set the list, we also notify the adapter of our changes
    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    // Here we inflate the layout for our items and create a view holder
    // by passing in the inflated layout/view hierarchy
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);

        return new ContactViewHolder(view);
    }

    // Here we update our view holder with new data for a specific position
    // in our list
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.update(contactList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
