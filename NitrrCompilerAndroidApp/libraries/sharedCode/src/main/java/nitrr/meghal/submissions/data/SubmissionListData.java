package nitrr.meghal.submissions.data;

import java.util.List;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class SubmissionListData {
    private boolean success;
    private String message;
    private List<SubmissionDetails> submission_list;

    public SubmissionListData(boolean success, String message, List<SubmissionDetails> submission_list) {
        this.success = success;
        this.message = message;
        this.submission_list = submission_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<SubmissionDetails> getSubmission_list() {
        return submission_list;
    }
}
