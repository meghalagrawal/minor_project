package nitrr.meghal.compiler.data;

/**
 * Created by meghal on 4/12/17.
 */

public class SubmitResponse {
	private String message;
	private boolean success;

	public SubmitResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}
}
