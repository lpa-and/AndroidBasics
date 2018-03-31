package ui;

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

import at.technikumwien.birthdaynotifier.R;
import message.MessageActivity;
import message.MessageFragment;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText userInput = getView().findViewById(R.id.userInput);
        Button activityButton = getView().findViewById(R.id.activityButton);
        Button fragmentButton = getView().findViewById(R.id.fragmentButton);

        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MessageActivity.class);
                intent.putExtra(MainActivity.MESSAGE, userInput.getText().toString());
                startActivity(intent);
            }
        });

        fragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    public MessageFragment messageToFragment(String theMessage){

        Bundle bundle = new Bundle();
        MessageFragment fragment = new MessageFragment();

        bundle.putString(MainActivity.MESSAGE, theMessage);
        fragment.setArguments(bundle);

        return fragment;
    }


}//end of fragment MainFragment
