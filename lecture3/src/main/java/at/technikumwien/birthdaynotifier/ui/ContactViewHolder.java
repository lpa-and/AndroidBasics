package at.technikumwien.birthdaynotifier.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.technikumwien.birthdaynotifier.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    private View itemView;

    private TextView name;
    private TextView birthdate;

    public ContactViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.name = itemView.findViewById(R.id.name);
        this.birthdate = itemView.findViewById(R.id.birthday);
    }

    public void update(String newData) {
        name.setText(newData);
    }
}
