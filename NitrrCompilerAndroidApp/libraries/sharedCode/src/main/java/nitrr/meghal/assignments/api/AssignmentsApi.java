package nitrr.meghal.assignments.api;

import nitrr.meghal.Urls;
import nitrr.meghal.assignments.data.AssignmentListData;
import nitrr.meghal.assignments.data.ClassAddData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public interface AssignmentsApi {

    @GET(Urls.REQUEST_ASSIGNMENT_LIST)
    Call<AssignmentListData> requestAssignmentList(@Query("access_token") String access_token, @Query("class_id") int class_id);
    @GET(Urls.REQUEST_ASSIGNMENT_LIST)
    Call<ClassAddData> requestAddClass(@Query("access_token") String access_token,
                                       @Query("class_code") String class_code);
}
