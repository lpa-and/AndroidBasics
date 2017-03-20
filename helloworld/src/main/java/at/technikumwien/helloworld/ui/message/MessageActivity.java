package at.technikumwien.helloworld.ui.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import at.technikumwien.helloworld.R;

public class MessageActivity extends AppCompatActivity {

    // this static final variable is used as a key
    // for setting the extra on the activity intent
    private static final String EXTRA_MESSAGE = "message";

    public static Intent getIntent(Context ctx, String message) {
        Intent intent = new Intent(ctx, MessageActivity.class);
        // To pass data to an activity, put extra data in the
        // intent that is used for starting it
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);

        // Enable the back button of our activity. By setting
        // parentActivityName in the manifest, Android automatically
        // handles going back to the correct Activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the message extra from the intent
        String message = getIntent().getStringExtra(EXTRA_MESSAGE);

        // Set the message String on our TextView
        TextView textView = (TextView) findViewById(R.id.message);
        textView.setText(message);
    }
}
