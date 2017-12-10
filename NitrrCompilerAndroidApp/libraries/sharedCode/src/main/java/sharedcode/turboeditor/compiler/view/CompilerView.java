package sharedcode.turboeditor.compiler.view;

/**
 * Created by ujjwal on 4/12/17.
 */

public interface CompilerView {

	void showLoader(boolean show);
	void showOutput(String message);

	void showRunTime(float time);

	void showError(String errors);
}
