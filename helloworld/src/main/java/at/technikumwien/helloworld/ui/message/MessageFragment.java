package at.technikumwien.helloworld.ui.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import at.technikumwien.helloworld.R;

public class MessageFragment extends Fragment {

    // this static final variable is used as a key
    // for setting the data on the argument bundle
    private static final String EXTRA_MESSAGE = "message";

    public static Fragment getFragment(String message) {
        MessageFragment fragment = new MessageFragment();

        // To pass arguments to a fragment, create a bundle
        // and add your data to it. Then call setArguments()
        // on the fragment.
        Bundle args = new Bundle();
        args.putString(EXTRA_MESSAGE, message);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the xml layout and return the root view
        return inflater.inflate(R.layout.message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the message String from the arguments
        String message = getArguments().getString(EXTRA_MESSAGE);

        // Set the message String on our TextView
        TextView textView = (TextView) getView().findViewById(R.id.message);
        textView.setText(message);
    }

}
