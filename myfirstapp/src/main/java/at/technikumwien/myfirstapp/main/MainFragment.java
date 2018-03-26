package at.technikumwien.myfirstapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import at.technikumwien.myfirstapp.R;
import at.technikumwien.myfirstapp.message.MessageActivity;
import at.technikumwien.myfirstapp.message.MessageFragment;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_main, container, false);
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText message = getView().findViewById(R.id.message);
        Button sendActivity = getView().findViewById(R.id.send_activity);
        Button sendFragment = getView().findViewById(R.id.send_fragment);

        sendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity with message
                Intent intent = new Intent(getContext(), MessageActivity.class);
                String userInputInEditText = message.getText().toString();
                intent.putExtra(MessageActivity.EXTRA_MESSAGE, userInputInEditText);
                startActivity(intent);
            }
        });

        sendFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start fragment with message

                String userInputInEditText = message.getText().toString();
                MessageFragment fragment = MessageFragment.getFragment(userInputInEditText);

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
