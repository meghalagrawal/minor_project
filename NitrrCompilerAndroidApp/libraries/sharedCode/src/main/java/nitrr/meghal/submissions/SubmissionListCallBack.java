package nitrr.meghal.submissions;

import nitrr.meghal.submissions.data.SubmissionListData;

public interface SubmissionListCallBack {
    void onSuccess(SubmissionListData submissionListData);
    void onFailure(String message);
}
