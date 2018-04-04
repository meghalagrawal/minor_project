package nitrr.meghal.login.api;

import nitrr.meghal.Urls;
import nitrr.meghal.login.data.LoginData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public interface LoginApi {

    @FormUrlEncoded
    @POST(Urls.REQUEST_LOGIN)
    Call<LoginData> requestLogin(@Field("email") String email,
                                 @Field("password") String  password);


}
