package at.technikumwien.lecture5.ui.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import at.technikumwien.lecture5.R;
import at.technikumwien.lecture5.data.model.Event;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Event> eventList = Collections.emptyList();

    public void setList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);

        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.update(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
