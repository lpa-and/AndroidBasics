package at.technikumwien.myfirstapp.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import at.technikumwien.myfirstapp.R;

public class MessageActivity extends AppCompatActivity{

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        TextView message = findViewById(R.id.message);
        message.setText(getIntent().getStringExtra(EXTRA_MESSAGE));
    }
}
