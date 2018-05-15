package nitrr.meghal.assignments.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import nitrr.meghal.R;
import nitrr.meghal.Urls;
import nitrr.meghal.assignments.api.AssignmentsApi;
import nitrr.meghal.assignments.data.AssignmentDetails;
import nitrr.meghal.assignments.data.ClassAddData;
import nitrr.meghal.assignments.data.AssignmentListData;
import nitrr.meghal.helper.SharedPrefs;
import nitrr.meghal.classes.view.ClassListAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AssignmentsActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Button joinNewClass;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private SharedPrefs sharedPrefs;
    private AssignmentListAdapter assignmentListAdapter;
    private int class_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        class_id= b.getInt("class_id");

        toolbar = findViewById(R.id.toolbar);
        joinNewClass = findViewById(R.id.joinClass);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        sharedPrefs = new SharedPrefs(this);
        progressDialog = new ProgressDialog(this);
        assignmentListAdapter =new AssignmentListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(assignmentListAdapter);

        progressDialog.setTitle("Connecting to servers . .");
        progressDialog.setMessage("Checking for the class . .");
//        joinNewClass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showAddClassDialog();
//
//            }
//        });
        showClassesList();
    }

    void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    void showAddClassDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.class_add_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Enter Class Code");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                startAddClassProcess(edt.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }
    void startAddClassProcess(String class_code){
        progressDialog.show();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AssignmentsApi assignmentsApi = retrofit.create(AssignmentsApi.class);


        Call<ClassAddData> call = assignmentsApi.requestAddClass(sharedPrefs.getAccessToken(),class_code);

        call.enqueue(new Callback<ClassAddData>() {
            @Override
            public void onResponse(Call<ClassAddData> call, Response<ClassAddData> response) {
                if (response.body().isSuccess()) {

                    Toast.makeText(AssignmentsActivity.this, "Class added successfully", Toast.LENGTH_SHORT).show();
                    showClassesList();

                } else {
                    showMessage(response.body().getMessage());
                }
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<ClassAddData> call, Throwable t) {
                t.printStackTrace();
                progressDialog.hide();
                showMessage(t.getMessage());
            }
        });
    }
    void showClassesList(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        AssignmentsApi assignmentsApi = retrofit.create(AssignmentsApi.class);

        Call<AssignmentListData> call = assignmentsApi.requestAssignmentList(sharedPrefs.getAccessToken(),class_id);

        call.enqueue(new Callback<AssignmentListData>() {
            @Override
            public void onResponse(Call<AssignmentListData> call, Response<AssignmentListData> response) {
                if (response.body().isSuccess()) {
                    setData(response.body());

                } else {
                    showMessage(response.body().getMessage());
                }
                progressBar.setVisibility(View.GONE);
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<AssignmentListData> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                progressDialog.hide();
                showMessage(t.getMessage());
            }
        });

    }
    public void setData(AssignmentListData assignmentListData) {
        assignmentListAdapter.setData(assignmentListData.getAssignment_list());
        assignmentListAdapter.notifyDataSetChanged();
    }


}
