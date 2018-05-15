package nitrr.meghal.assignments.data;

import java.util.List;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class AssignmentListData {
    private boolean success;
    private String message;
    private List<AssignmentDetails> assignment_list;

    public AssignmentListData(boolean success, String message, List<AssignmentDetails> assignment_list) {
        this.success = success;
        this.message = message;
        this.assignment_list = assignment_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<AssignmentDetails> getAssignment_list() {
        return assignment_list;
    }
}
