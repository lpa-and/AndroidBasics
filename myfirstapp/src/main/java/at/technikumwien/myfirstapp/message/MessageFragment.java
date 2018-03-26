package at.technikumwien.myfirstapp.message;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import at.technikumwien.myfirstapp.R;

public class MessageFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "message";

    public static MessageFragment getFragment(String message) {
        MessageFragment fragment = new MessageFragment();

        Bundle arguments = new Bundle();
        // set arguments
        arguments.putString(EXTRA_MESSAGE, message);


        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView message = getView().findViewById(R.id.message);
        String messageFromArguments = getArguments().getString(EXTRA_MESSAGE);
        message.setText(messageFromArguments);
    }
}
