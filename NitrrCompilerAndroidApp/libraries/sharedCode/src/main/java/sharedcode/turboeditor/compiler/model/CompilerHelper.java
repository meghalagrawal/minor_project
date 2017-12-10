package sharedcode.turboeditor.compiler.model;

import sharedcode.turboeditor.compiler.CompilerCallback;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerHelper {
	void compileCode(int language, String code, String stdin, CompilerCallback compilerCallback);
}
