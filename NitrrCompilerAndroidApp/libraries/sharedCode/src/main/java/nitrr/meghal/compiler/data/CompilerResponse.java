package nitrr.meghal.compiler.data;

/**
 * Created by meghal on 4/12/17.
 */

public class CompilerResponse {
	private String output;
	private int langid;
	private String code;
	private String errors;
	private float time;

	public CompilerResponse(String output, int langid, String code, String errors, float time) {
		this.output = output;
		this.langid = langid;
		this.code = code;
		this.errors = errors;
		this.time = time;
	}

	public String getOutput() {
		return output;
	}

	public int getLangid() {
		return langid;
	}

	public String getCode() {
		return code;
	}

	public String getErrors() {
		return errors;
	}

	public float getTime() {
		return time;
	}
}
