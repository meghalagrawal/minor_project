package nitrr.meghal.submissions.data;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class SubmissionDetails {
    private int submission_id;
    private String created;
    private float time_taken;
    private String response;

    public SubmissionDetails(int submission_id, String created, float time_taken, String response) {
        this.submission_id = submission_id;
        this.created = created;
        this.time_taken = time_taken;
        this.response = response;
    }

    public int getSubmission_id() {
        return submission_id;
    }

    public String getCreated() {
        return created;
    }

    public float getTime_taken() {
        return time_taken;
    }

    public String getResponse() {
        return response;
    }
}
