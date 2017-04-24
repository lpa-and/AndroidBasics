package at.technikumwien.lecture5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton button;

    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        button = (FloatingActionButton) findViewById(R.id.load_data);

        adapter = new MessageAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDataLoaded(
                        Arrays.asList("Titel 1", "Titel 2"),
                        Arrays.asList("Nachricht.", "Dies ist noch eine Nachricht")
                );
            }
        });
    }

    private void onDataLoaded(List<String> titleList, List<String> messageList) {
        adapter.setLists(titleList, messageList);
    }

}
