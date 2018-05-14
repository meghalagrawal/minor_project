package nitrr.meghal.compiler.api;

import nitrr.meghal.compiler.data.SubmitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import nitrr.meghal.Urls;
import nitrr.meghal.compiler.data.CompilerRequest;
import nitrr.meghal.compiler.data.CompilerResponse;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerApi {
    @FormUrlEncoded
    @POST(Urls.SUB_URL_COMPILER)
    Call<CompilerResponse>
    compileCode(@Field("stdin") String stdin, @Field("code") String code, @Field("language") int language);


    @FormUrlEncoded
    @POST(Urls.SUB_URL_SUBMIT_ASSIGNMENT)
    Call<SubmitResponse>
    submitCode(@Field("access_token") String access_token, @Field("stdin") String stdin,
               @Field("code") String code, @Field("language") int language, @Field("assignment_id") int assignment_id);
}
