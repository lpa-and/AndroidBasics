package at.technikumwien.birthdaynotifier.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import at.technikumwien.birthdaynotifier.R;
import at.technikumwien.birthdaynotifier.ui.main.recyclerview.ContactAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TextView emptyText;

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view and find our views
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        emptyText = (TextView) findViewById(R.id.no_contacts);

        // Create an adapter for the RecyclerView
        adapter = new ContactAdapter();

        // If the recycler view does not change in size, this
        // enables some optimizations
        recyclerView.setHasFixedSize(true);
        // Set a LinearLayoutManager, which lays the items out one after another
        // (like a LinearLayout)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set our contacts adapter on the RecyclerView
        recyclerView.setAdapter(adapter);

        // Show a snackbar when the floating action button is clicked
        // and add some data to show
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "Kontakte geladen", Snackbar.LENGTH_LONG).show();
                onContactsLoaded(Arrays.asList(
                        "Max Mustermann",
                        "Maria Musterfrau",
                        "Stefan Müller",
                        "Theresa Huber",
                        "Manuel Mustermann",
                        "Stefanie Musterfrau",
                        "Thomas Müller",
                        "Sarah Huber"
                ));
            }
        });
    }

    // Is called, when contacts are loaded. Here we set the visibility
    // of the empty list info text and update the data in our adapter
    private void onContactsLoaded(List<String> contactList) {
        emptyText.setVisibility(contactList.isEmpty() ? View.VISIBLE : View.GONE);
        adapter.setContactList(contactList);
    }
}
