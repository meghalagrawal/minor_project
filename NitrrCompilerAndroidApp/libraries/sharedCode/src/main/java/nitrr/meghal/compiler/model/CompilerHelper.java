package nitrr.meghal.compiler.model;

import nitrr.meghal.compiler.CompilerCallback;
import nitrr.meghal.compiler.SubmitCallback;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerHelper {
	void compileCode(int language, String code, String stdin, CompilerCallback compilerCallback);

    void submitCode(String access_token, int language, String code, String stdin, int assignment_id, SubmitCallback submitCallback);
}
