package at.technikumwien.helloworld.ui.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import at.technikumwien.helloworld.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Our MainActivity only serves as a fragment container
         * for our MainFragment.
         *
         * The FragmentManager itself "saves" all the fragments that
         * are currently displayed on the screen when the Activity is killed.
         * Therefore we only need to add the MainFragment to the container
         * when we know, that the Activity is freshly started, via checking the
         * savedInstanceState for null */
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    /* The add method really just adds the fragment to the
                     * container, regardless of whether there already is a
                     * fragment in there. For replacing purposes, there is a
                     * replace() method. */
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }
}
