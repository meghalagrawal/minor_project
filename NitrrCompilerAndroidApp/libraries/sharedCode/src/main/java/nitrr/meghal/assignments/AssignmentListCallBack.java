package nitrr.meghal.assignments;

import nitrr.meghal.assignments.data.AssignmentListData;

public interface AssignmentListCallBack {
    void onSuccess(AssignmentListData assignmentListData);
    void onFailure(String message);
}
