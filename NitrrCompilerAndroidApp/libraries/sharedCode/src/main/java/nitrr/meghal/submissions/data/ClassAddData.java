package nitrr.meghal.submissions.data;


public class ClassAddData {
    private String message;

    public ClassAddData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    private boolean success;


}
