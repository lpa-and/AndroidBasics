package at.technikumwien.lecture5;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<String> titleList = Collections.emptyList();
    private List<String> messageList = Collections.emptyList();

    public void setLists(List<String> titleList, List<String> messageList) {
        this.titleList = titleList;
        this.messageList = messageList;
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
        holder.update(titleList.get(position), messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
}
