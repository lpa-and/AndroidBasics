package at.technikumwien.lecture5.ui;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import android.util.Log;
import android.view.View;

import java.util.List;

import at.technikumwien.MyApplication;
import at.technikumwien.lecture5.R;
import at.technikumwien.lecture5.data.local.ContentResolverEventRepo;
import at.technikumwien.lecture5.data.local.EventRepo;
import at.technikumwien.lecture5.data.local.OnDataLoadCallback;
import at.technikumwien.lecture5.data.model.Event;
import at.technikumwien.lecture5.receiver.AlarmReceiver;
import at.technikumwien.lecture5.ui.recyclerview.MessageAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 123;

    private RecyclerView recyclerView;
    private FloatingActionButton button;

    private MessageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        button = (FloatingActionButton) findViewById(R.id.load_data);

        adapter = new MessageAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndLoadData();
            }
        });
    }

    private void checkPermissionAndLoadData() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CALENDAR }, REQUEST_CODE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CODE_PERMISSION) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted();
            } else {
                onPermissionDenied();
            }
        }
    }

    private void onPermissionGranted() {
        MyApplication.getEventRepo().findAllEventsInFuture(new OnDataLoadCallback<List<Event>>() {
            @Override
            public void onDataLoaded(List<Event> data) {
                // Do something with the data
                Log.i("MainActivity", "List loaded: " + data.size() + " items");
                onEventsLoaded(data);
            }

            @Override
            public void onDataLoadError(Exception exception) {
                Log.e("MainActivity", "Loading event list failed", exception);
                Snackbar.make(recyclerView, "Konnte Veranstaltungen nicht laden", Snackbar.LENGTH_LONG)
                        .setAction("Erneut versuchen", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkPermissionAndLoadData();
                            }
                        })
                        .show();
            }
        });
    }

    private void onPermissionDenied() {
        Snackbar.make(recyclerView, "Benötige Berechtigung um Kalendereinträge anzuzeigen", Snackbar.LENGTH_LONG)
                .setAction("Erneut anfragen", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPermissionAndLoadData();
                    }
                })
                .show();
    }

    private void onEventsLoaded(List<Event> eventList) {
        adapter.setList(eventList);
    }

}
