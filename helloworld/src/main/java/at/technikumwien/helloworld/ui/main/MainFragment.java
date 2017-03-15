package at.technikumwien.helloworld.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.technikumwien.helloworld.R;
import at.technikumwien.helloworld.databinding.FragmentMainBinding;
import at.technikumwien.helloworld.ui.message.MessageActivity;
import at.technikumwien.helloworld.ui.message.MessageFragment;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Call the method to save our instance state in the view model
        viewModel.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create our view model and provide the dependencies we need
        viewModel = new MainViewModel(getContext(), getFragmentManager());
        // Call the method to restore our instance state in the view model
        viewModel.onRestoreInstanceState(savedInstanceState);

        // create the binding for the layout file. Use DataBindingUtil.inflate() for fragments
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        // set the vm (ViewModel) variable on our binding
        binding.setVm(viewModel);
        // return the root view of our layout
        return binding.getRoot();
    }

    /**
     * The view model is an object that contains the data
     * and the actions/commands that our view/layout needs.
     *
     * Data is provided via properties (getter/setters).
     * In our case, we only have a message String as data.
     *
     * Actions are provided as methods. In our case, we
     * have two actions when two buttons are clicked. In order
     * to support binding the action directly to the onClick
     * attribute in the layout, the method signature (parameters)
     * need to match the {@link View.OnClickListener#onClick(View)} method.
     */
    public static class MainViewModel {

        // this static final variable is used as a key
        // for saving state when the activity is killed
        private static final String KEY_MESSAGE = "message";

        // Context is needed for starting new activities
        private final Context ctx;
        // The FragmentManager is needed to replace fragments
        private final FragmentManager fm;

        private String message;

        // Inject the dependencies that are needed by your view model
        MainViewModel(Context ctx, FragmentManager fm) {
            this.ctx = ctx;
            this.fm = fm;
        }

        // In this method, we save our state (in our case
        // the message String)
        public void onSaveInstanceState(Bundle outState) {
            outState.putString(KEY_MESSAGE, message);
        }

        // In this method, we restore our state (in our case
        // the message String). Do not forget to check whether
        // the savedInstanceState is null, before restoring state.
        public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
            if(savedInstanceState != null) {
                message = savedInstanceState.getString(KEY_MESSAGE);
            }
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        // This command is executed when the "Send (Activity)" button is clicked
        public void onSendActivityClick(View v) {
            // Here we use the context to start an Activity.
            ctx.startActivity(MessageActivity.getIntent(ctx, message));
        }

        // This command is executed when the "Send (Fragment)" button is clicked
        public void onSendFragmentClick(View v) {
            // FragmentManager executes changes in fragments based on a transaction.
            // This means, every change needs to be done in a transaction via
            // createTransaction() and finally commit().
            fm.beginTransaction()
                    // this replaces the current fragment (this Fragment) in the given
                    // container with a new MessageFragment
                    .replace(R.id.container, MessageFragment.getFragment(message))
                    // this adds the transaction on the back stack -> remember, this is
                    // our card stack of fragments. If addToBackStack() is not called,
                    // the current fragment in the container is discarded.
                    .addToBackStack(null)
                    .commit();
        }
    }
}
