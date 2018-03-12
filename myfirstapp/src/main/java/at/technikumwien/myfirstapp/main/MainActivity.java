package at.technikumwien.myfirstapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import at.technikumwien.myfirstapp.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        } else {
            // fragment manager already has the fragment
        }

    }
}
