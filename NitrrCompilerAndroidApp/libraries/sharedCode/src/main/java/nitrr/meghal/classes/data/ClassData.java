package nitrr.meghal.classes.data;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class ClassData {
    private int class_id;
    private String title;
    private String description;

    public ClassData(int class_id, String title, String description) {
        this.class_id = class_id;
        this.title = title;
        this.description = description;
    }

    public int getClass_id() {
        return class_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
