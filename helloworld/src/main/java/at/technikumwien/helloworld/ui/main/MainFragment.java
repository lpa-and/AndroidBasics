package at.technikumwien.helloworld.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import at.technikumwien.helloworld.R;
import at.technikumwien.helloworld.ui.message.MessageActivity;
import at.technikumwien.helloworld.ui.message.MessageFragment;

public class MainFragment extends Fragment {

    // this static final variable is used as a key
    // for saving state when the activity is killed
    private static final String KEY_MESSAGE = "message";

    private EditText message;
    private Button sendActivity;
    private Button sendFragment;

    // In this method, we save our state (in our case
    // the message String)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Call the method to save our instance state in the view model
        outState.putString(KEY_MESSAGE, getMessage());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the xml layout and return the root view
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get references of the views from the XML layout
        message = (EditText) getView().findViewById(R.id.message);
        sendActivity = (Button) getView().findViewById(R.id.send_activity);
        sendFragment = (Button) getView().findViewById(R.id.send_fragment);

        // Here, we restore our saved state (in our case
        // the message String). Do not forget to check whether
        // the savedInstanceState is null, before restoring state.
        // This occurs, when the Activity/Fragment is first created
        // and therefore has no state.
        if(savedInstanceState != null) {
            message.setText(savedInstanceState.getString(KEY_MESSAGE));
        }

        sendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MessageActivity.getIntent(getContext(), getMessage()));
            }
        });

        sendFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FragmentManager executes changes in fragments based on a transaction.
                // This means, every change needs to be done in a transaction via
                // createTransaction() and finally commit().
                getFragmentManager().beginTransaction()
                        // this replaces the current fragment (this Fragment) in the given
                        // container with a new MessageFragment
                        .replace(R.id.container, MessageFragment.getFragment(getMessage()))
                        // this adds the transaction on the back stack -> remember, this is
                        // our card stack of fragments. If addToBackStack() is not called,
                        // the current fragment in the container is discarded.
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private String getMessage() {
        return message.getText().toString();
    }
}
