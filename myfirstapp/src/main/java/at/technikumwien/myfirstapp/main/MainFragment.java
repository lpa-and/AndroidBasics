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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.fragment_main, container, false);

        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText message = getView().findViewById(R.id.message);
        Button sendActivity = getView().findViewById(R.id.send_acivity);
        Button sendFragment = getView().findViewById(R.id.send_fragment);

        sendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start activity with message
                Intent intent = new Intent(getContext(), MessageActivity.class);
                intent.putExtra(MessageActivity.EXTRA_MESSAGE, message.getText().toString());
                startActivity(intent);
            }
        });

        sendFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //start fragment with message
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, MessageFragment.getMessageFragment(message.getText().toString()))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}
