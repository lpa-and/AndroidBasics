package at.technikumwien.helloworld.ui.message;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.technikumwien.helloworld.R;
import at.technikumwien.helloworld.databinding.MessageBinding;

public class MessageFragment extends Fragment {

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

    private MessageBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create the binding for our layout and return the root view
        binding = DataBindingUtil.inflate(inflater, R.layout.message, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set the message variable
        binding.setMessage(getArguments().getString(EXTRA_MESSAGE));
    }

}
