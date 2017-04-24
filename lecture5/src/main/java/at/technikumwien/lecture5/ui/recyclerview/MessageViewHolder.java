package at.technikumwien.lecture5.ui.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.technikumwien.lecture5.R;
import at.technikumwien.lecture5.data.model.Event;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView messageTextView;

    public MessageViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.title);
        messageTextView = (TextView) itemView.findViewById(R.id.event);
    }

    public void update(Event event) {
        titleTextView.setText(event.title());
        //messageTextView.setText(event.date());
    }
}
