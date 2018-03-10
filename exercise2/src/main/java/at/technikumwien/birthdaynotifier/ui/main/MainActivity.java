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

    private TextView emptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view and find our views
        setContentView(R.layout.activity_main);
    }

}
