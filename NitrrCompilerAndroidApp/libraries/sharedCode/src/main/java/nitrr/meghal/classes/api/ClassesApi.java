package nitrr.meghal.classes.api;

import nitrr.meghal.Urls;
import nitrr.meghal.classes.data.ClassAddData;
import nitrr.meghal.classes.data.ClassListData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public interface ClassesApi {

    @GET(Urls.REQUEST_CLASS_LIST)
    Call<ClassListData> requestClassList(@Query("access_token") String access_token);

    @FormUrlEncoded
    @POST(Urls.REQUEST_ADD_CLASS)
    Call<ClassAddData> requestAddClass(@Field("access_token") String access_token,
                                       @Field("class_code")String  class_code);
}
