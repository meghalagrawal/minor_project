package nitrr.meghal.classes.data;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class ClassListData {
    private boolean success;
    private String message;
    private ClassData class_list;

    public ClassListData(boolean success, String message, ClassData class_list) {
        this.success = success;
        this.message = message;
        this.class_list = class_list;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ClassData getClass_list() {
        return class_list;
    }
}
