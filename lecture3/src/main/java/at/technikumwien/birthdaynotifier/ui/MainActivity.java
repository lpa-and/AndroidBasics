package at.technikumwien.birthdaynotifier.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.technikumwien.birthdaynotifier.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private TextView emptyText;

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        addButton = findViewById(R.id.fab);
        emptyText = findViewById(R.id.empty_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> contactList = new ArrayList<>();
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");
        contactList.add("Max Mustermann");
        contactList.add("Maria Musterfrau");
        contactList.add("Patrick Löwenstein");

        adapter = new ContactAdapter();
        recyclerView.setAdapter(adapter);

        getApplicationContext();
        //emptyText.setText(getString(R.string.empty_text));

        onContactsLoaded(contactList);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Button clicked", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void onContactsLoaded(List<String> contactList) {
        emptyText.setVisibility(contactList.isEmpty() ? View.VISIBLE : View.GONE);
        adapter.setList(contactList);
    }

}
