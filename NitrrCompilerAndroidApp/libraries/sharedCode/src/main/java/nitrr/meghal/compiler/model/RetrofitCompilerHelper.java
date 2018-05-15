package nitrr.meghal.compiler.model;

import nitrr.meghal.compiler.SubmitCallback;
import nitrr.meghal.compiler.data.SubmitResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import nitrr.meghal.Urls;
import nitrr.meghal.compiler.CompilerCallback;
import nitrr.meghal.compiler.api.CompilerApi;
import nitrr.meghal.compiler.data.CompilerRequest;
import nitrr.meghal.compiler.data.CompilerResponse;

/**
 * Created by meghal on 4/12/17.
 */

public class RetrofitCompilerHelper implements CompilerHelper {

	private Call<CompilerResponse> call;
	private Call<SubmitResponse> submitCall;

	@Override
	public void compileCode(int language, String code, String stdin, final CompilerCallback compilerCallback) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		Retrofit retrofit =new Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create()).baseUrl(Urls.BASE_URL).build();
		final CompilerApi compilerApi =retrofit.create(CompilerApi.class);
		call = compilerApi.compileCode(stdin,code,language);
		call.enqueue(new Callback<CompilerResponse>() {
			@Override
			public void onResponse(Call<CompilerResponse> call, Response<CompilerResponse> response) {
				compilerCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<CompilerResponse> call, Throwable t) {
				compilerCallback.onFailure();
				t.printStackTrace();
			}
		});
	}

	@Override
	public void submitCode(String access_token, int language, String code, String stdin, int assignment_id, final SubmitCallback submitCallback) {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		Retrofit retrofit =new Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create()).baseUrl(Urls.BASE_URL).build();
		final CompilerApi compilerApi =retrofit.create(CompilerApi.class);
		submitCall = compilerApi.submitCode(access_token,stdin,code,language,assignment_id);
		submitCall.enqueue(new Callback<SubmitResponse>() {
			@Override
			public void onResponse(Call<SubmitResponse> call, Response<SubmitResponse> response) {
				submitCallback.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<SubmitResponse> call, Throwable t) {
				submitCallback.onFailure();
				t.printStackTrace();
			}
		});
	}
}
