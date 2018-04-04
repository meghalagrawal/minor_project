package nitrr.meghal.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import nitrr.meghal.R;
import nitrr.meghal.Urls;
import nitrr.meghal.classes.ClassListActivity;
import nitrr.meghal.helper.SharedPrefs;
import nitrr.meghal.login.api.LoginApi;
import nitrr.meghal.login.data.LoginData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {

    Toolbar toolbar;
    EditText email, password;
    Button login;
    private ProgressDialog progressDialog;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.button_login);
        progressDialog = new ProgressDialog(this);

        progressDialog.setTitle("Connecting to servers . .");
        progressDialog.setMessage("Logging In . .");

        sharedPrefs = new SharedPrefs(this);

        if (sharedPrefs.getAccessToken() != null) {
            Intent intent = new Intent(LoginActivity.this, ClassListActivity.class);

            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                LoginApi loginApi = retrofit.create(LoginApi.class);


                Call<LoginData> call = loginApi.requestLogin(email.getText().toString(), password.getText().toString());

                call.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if (response.body().isSuccess()) {

                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            sharedPrefs.setAccessToken(response.body().getAccess_token());

                            Intent intent = new Intent(LoginActivity.this, ClassListActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            showMessage(response.body().getMessage());
                        }
                        progressDialog.hide();
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.hide();
                        showMessage(t.getMessage());

                    }
                });

            }
        });
    }

    void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
