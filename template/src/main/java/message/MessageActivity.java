package message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import at.technikumwien.birthdaynotifier.R;
import ui.MainActivity;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        TextView message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra(MainActivity.MESSAGE));
    }
}
