package at.technikumwien.birthdaynotifier.ui.main.recyclerview;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import at.technikumwien.birthdaynotifier.R;

/**
 * Please read the {@link RecyclerView.ViewHolder} java docs.
 */
class ContactViewHolder extends RecyclerView.ViewHolder {

    private CardView cardView;
    private TextView name;

    ContactViewHolder(View itemView) {
        super(itemView);

        // Find our views
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        name = (TextView) itemView.findViewById(R.id.name);

        // Show a Snackbar when the card view is clicked
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cardView, name.getText() + " clicked", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // Here we set the new item model on our view holder and
    // update our views accordingly
    void update(String contactName) {
        name.setText(contactName);
    }
}
