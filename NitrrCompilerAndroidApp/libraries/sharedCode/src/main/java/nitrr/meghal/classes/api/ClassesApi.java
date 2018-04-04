package nitrr.meghal.classes.api;

import nitrr.meghal.Urls;
import nitrr.meghal.classes.data.ClassListData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public interface ClassesApi {

    @GET(Urls.REQUEST_CLASS_LIST)
    Call<ClassListData> requestClassList(@Query("access_token") String access_token);
}
