package sharedcode.turboeditor.compiler.data;

import retrofit2.http.Field;

/**
 * Created by meghal on 4/12/17.
 */

public class CompilerRequest {
	private String stdin;
	private int language;
	private String code;

	public CompilerRequest(String stdin, int language, String code) {
		this.stdin = stdin;
		this.language = language;
		this.code = code;
	}

	public void setStdin(String stdin) {
		this.stdin = stdin;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
