package at.technikumwien.helloworld.ui.message;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import at.technikumwien.helloworld.R;
import at.technikumwien.helloworld.databinding.MessageBinding;

public class MessageActivity extends AppCompatActivity {

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
        // Enable the back button of our activity. By setting
        // parentActivityName in the manifest, Android automatically
        // handles going back to the correct Activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the binding for our layout and set the message variable
        MessageBinding binding = DataBindingUtil.setContentView(this, R.layout.message);
        binding.setMessage(getIntent().getStringExtra(EXTRA_MESSAGE));
    }
}
