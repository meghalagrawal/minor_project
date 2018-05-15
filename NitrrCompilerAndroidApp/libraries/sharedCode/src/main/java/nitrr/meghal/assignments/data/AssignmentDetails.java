package nitrr.meghal.assignments.data;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class AssignmentDetails {
    private int assignment_id;
    private String title;
    private String description;
    private String due_date;
    private String time_limit;


    public AssignmentDetails(int assignment_id, String title, String description, String due_date, String time_limit) {
        this.assignment_id = assignment_id;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.time_limit = time_limit;
    }

    public int getAssignment_id() {
        return assignment_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getTime_limit() {
        return time_limit;
    }
}
