package nitrr.meghal.classes.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import nitrr.meghal.classes.api.ClassesApi;
import nitrr.meghal.classes.data.ClassAddData;
import nitrr.meghal.classes.data.ClassData;
import nitrr.meghal.classes.data.ClassListData;
import nitrr.meghal.helper.SharedPrefs;
import nitrr.meghal.login.ClassListAdapter;
import nitrr.meghal.login.LoginActivity;
import nitrr.meghal.login.api.LoginApi;
import nitrr.meghal.login.data.LoginData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClassListActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private Button joinNewClass;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private SharedPrefs sharedPrefs;
    private ClassListAdapter classListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        toolbar = findViewById(R.id.toolbar);
        joinNewClass = findViewById(R.id.joinClass);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        sharedPrefs = new SharedPrefs(this);
        progressDialog = new ProgressDialog(this);
        classListAdapter=new ClassListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(classListAdapter);

        progressDialog.setTitle("Connecting to servers . .");
        progressDialog.setMessage("Checking for the class . .");
        joinNewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddClassDialog();

            }
        });
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
        ClassesApi classesApi = retrofit.create(ClassesApi.class);


        Call<ClassAddData> call = classesApi.requestAddClass(sharedPrefs.getAccessToken(),class_code);

        call.enqueue(new Callback<ClassAddData>() {
            @Override
            public void onResponse(Call<ClassAddData> call, Response<ClassAddData> response) {
                if (response.body().isSuccess()) {

                    Toast.makeText(ClassListActivity.this, "Class added successfully", Toast.LENGTH_SHORT).show();
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
        ClassesApi classesApi = retrofit.create(ClassesApi.class);


        Call<ClassListData> call = classesApi.requestClassList(sharedPrefs.getAccessToken());

        call.enqueue(new Callback<ClassListData>() {
            @Override
            public void onResponse(Call<ClassListData> call, Response<ClassListData> response) {
                if (response.body().isSuccess()) {
                    setData(response.body());

                } else {
                    showMessage(response.body().getMessage());
                }
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<ClassListData> call, Throwable t) {
                t.printStackTrace();
                progressDialog.hide();
                showMessage(t.getMessage());
            }
        });

    }
    public void setData(ClassListData classListData) {
        classListAdapter.setData((List<ClassData>) classListData.getClass_list());

        }


}
