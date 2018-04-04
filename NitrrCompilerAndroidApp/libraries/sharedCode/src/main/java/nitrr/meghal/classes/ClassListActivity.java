package nitrr.meghal.classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ProgressBar;

import nitrr.meghal.R;

public class ClassListActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Button joinNewClass;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        toolbar = findViewById(R.id.toolbar);
        joinNewClass = findViewById(R.id.joinClass);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);



    }
}
