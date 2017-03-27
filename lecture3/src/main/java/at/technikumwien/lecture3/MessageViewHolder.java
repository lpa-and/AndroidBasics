package at.technikumwien.lecture3;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView messageTextView;

    public MessageViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.title);
        messageTextView = (TextView) itemView.findViewById(R.id.message);
    }

    public void update(String title, String message) {
        titleTextView.setText(title);
        messageTextView.setText(message);
    }
}
