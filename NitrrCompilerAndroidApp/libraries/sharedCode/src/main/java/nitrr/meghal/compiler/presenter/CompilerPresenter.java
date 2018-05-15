package nitrr.meghal.compiler.presenter;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerPresenter {
	void compileCode(int language,String code,String stdin);
	void submitCode(String access_token, int language,String code,String stdin, int assignment_id);
}
