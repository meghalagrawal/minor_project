package nitrr.meghal.classes.data;

import java.util.List;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class ClassListData {
    private boolean success;
    private String message;
    private List<ClassData> class_list;

    public ClassListData(boolean success, String message, List<ClassData> class_list) {
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

    public List<ClassData> getClass_list() {
        return class_list;
    }
}
