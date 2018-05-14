package nitrr.meghal.submissions.api;

import nitrr.meghal.Urls;
import nitrr.meghal.submissions.data.SubmissionListData;
import nitrr.meghal.submissions.data.ClassAddData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public interface SubmissionsApi {

    @GET(Urls.REQUEST_SUBMISSION_LIST)
    Call<SubmissionListData> requestSubmissionList(@Query("access_token") String access_token, @Query("assignment_id") int class_id);
    @GET(Urls.REQUEST_SUBMISSION_LIST)
    Call<ClassAddData> requestAddClass(@Query("access_token") String access_token,
                                       @Query("class_code") String class_code);
}
