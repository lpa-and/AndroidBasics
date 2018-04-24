package at.technikumwien.birthdaynotifier.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import at.technikumwien.birthdaynotifier.BirthdayNotifierApplication;
import at.technikumwien.birthdaynotifier.R;
import at.technikumwien.birthdaynotifier.data.local.ContactRepo;
import at.technikumwien.birthdaynotifier.data.local.OnDataLoadCallback;
import at.technikumwien.birthdaynotifier.data.model.Contact;
import at.technikumwien.birthdaynotifier.ui.main.recyclerview.ContactAdapter;
import at.technikumwien.birthdaynotifier.util.Utils;

public class MainActivity extends AppCompatActivity {

    // When requesting permissions (or results/data from another Activity),
    // a request code is needed to identify the request later, when
    // you receive the result
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 345;

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TextView emptyText;

    private ContactAdapter adapter;
    private ContactRepo contactRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view and find our views
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        emptyText = findViewById(R.id.no_contacts);

        // Create an adapter for the RecyclerView
        adapter = new ContactAdapter();
        contactRepo = ((BirthdayNotifierApplication) getApplication()).getContactRepo();

        // If the recycler view does not change in size, this
        // enables some optimizations
        recyclerView.setHasFixedSize(true);
        // Set a LinearLayoutManager, which lays the items out one after another
        // (like a LinearLayout)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set our contacts adapter on the RecyclerView
        recyclerView.setAdapter(adapter);

        // Check permissions when the FAB is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkContactsPermissionAndLoadContacts();
            }
        });
    }

    private void checkContactsPermissionAndLoadContacts() {
        // First, check whether we already have the permission, if yes, we can
        // directly call our onContactsPermissionGranted() callback
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            onContactsPermissionGranted();
        } else {
            // If we don't already have the permission, request it.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE_PERMISSION_READ_CONTACTS);
        }
    }

    // After requesting a permission, the system calls this callback with the result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // First, check whether the request code matches
        if(requestCode == REQUEST_CODE_PERMISSION_READ_CONTACTS) {
            // Then check, whether the permission was granted or not
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onContactsPermissionGranted();
            } else {
                onContactsPermissionDenied();
            }
        }
    }

    // When permissions are granted, we show a snackbar and create some contacts
    // for onContactsLoaded()
    private void onContactsPermissionGranted() {
        Snackbar.make(recyclerView, "Permission granted", Snackbar.LENGTH_LONG).show();

        // Anonyme Klasse -> Implementieren und Instanz erstellen
        OnDataLoadCallback<List<Contact>> callback = new OnDataLoadCallback<List<Contact>>() {
            @Override
            public void onDataLoaded(List<Contact> data) {
                // When we arrive here, we have the list of contacts
                // as a parameter (data)

                onContactsLoaded(data);
            }

            @Override
            public void onDataLoadError(Exception exception) {

            }
        };

        // The callback is called when the operation
        // "findAllContactsWithBirthdays" is finished
        contactRepo.findAllContactsWithBirthdays(callback);
    }

    // When permissions are granted, we load contacts from our repository
    private void loadContacts() {

    }

    // When permissions are not granted, we show a snackbar with a hint that
    // the permission is needed. In the snackbar action, we call checkContactsPermissionAndLoadContacts()
    // again, to re-request the permission.
    private void onContactsPermissionDenied() {
        Snackbar.make(recyclerView, "Permission needed to show birthdays", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkContactsPermissionAndLoadContacts();
                    }
                }).show();
    }


    // Is called, when contacts are loaded. Here we set the visibility
    // of the empty list info text and update the data in our adapter
    private void onContactsLoaded(List<Contact> contactList) {
        emptyText.setVisibility(contactList.isEmpty() ? View.VISIBLE : View.GONE);
        adapter.setContactList(contactList);
    }
}
