package nitrr.meghal.login.data;

/**
 * Created by meghalagrawal on 04/04/18.
 */

public class LoginData {

    private boolean success;
    private String message;
    private String access_token;

    public LoginData(boolean success, String message, String access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }
}
