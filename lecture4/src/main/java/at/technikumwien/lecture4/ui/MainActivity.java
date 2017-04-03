package at.technikumwien.lecture4.ui;

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

import java.util.Arrays;
import java.util.List;

import at.technikumwien.lecture4.R;
import at.technikumwien.lecture4.data.Message;
import at.technikumwien.lecture4.ui.recyclerview.MessageAdapter;

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
                checkPermission();
            }
        });
    }

    private void checkPermission() {
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
        Snackbar.make(recyclerView, "Berechtigung erlaubt", Snackbar.LENGTH_LONG).show();

        onDataLoaded(Arrays.asList(
                Message.create("Title", "Message"),
                Message.create("Noch ein Titel", "Meine Nachricht"),
                Message.create("Noch ein Titel 2", "Meine Nachricht 3")
        ));
    }

    private void onPermissionDenied() {
        Snackbar.make(recyclerView, "Benötige Berechtigung um Kalendereinträge anzuzeigen", Snackbar.LENGTH_LONG)
                .setAction("Erneut anfragen", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkPermission();
                    }
                })
                .show();
    }

    private void onDataLoaded(List<Message> messageList) {
        adapter.setList(messageList);
    }

}
