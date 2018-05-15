package nitrr.meghal.compiler.presenter;

import nitrr.meghal.compiler.CompilerCallback;
import nitrr.meghal.compiler.SubmitCallback;
import nitrr.meghal.compiler.data.CompilerResponse;
import nitrr.meghal.compiler.data.SubmitResponse;
import nitrr.meghal.compiler.model.CompilerHelper;
import nitrr.meghal.compiler.view.CompilerView;

/**
 * Created by meghal on 4/12/17.
 */

public class CompilerPresenterImpl implements CompilerPresenter {
	private CompilerView compilerView;
	private CompilerHelper compilerHelper;

	public CompilerPresenterImpl(CompilerView compilerView, CompilerHelper compilerHelper) {
		this.compilerView = compilerView;
		this.compilerHelper = compilerHelper;
	}

	@Override
	public void compileCode(int language, String code, String stdin) {

		compilerView.showLoader(true);

		compilerHelper.compileCode(language, code, stdin, new CompilerCallback() {
			@Override
			public void onSuccess(CompilerResponse compilerResponse) {


				compilerView.showLoader(false);
				String error= compilerResponse.getErrors();

				if(error.length()==0){
					error = "No Error";
				}


				compilerView.showOutput("Output: "+compilerResponse.getOutput()+"\n\n"+"Compilation Time: "+compilerResponse.getTime()+"\n\nError: "+error);
//				compilerView.showRunTime(compilerResponse.getTime());
//				compilerView.showError(compilerResponse.getErrors());
			}

			@Override
			public void onFailure()
			{
				compilerView.showError("Unable to connect to server. . . .");
				compilerView.showLoader(false);
			}
		});
	}

	@Override
	public void submitCode(String access_token, int language, String code, String stdin, int assignment_id) {
		compilerView.showLoader(true);
		compilerHelper.submitCode(access_token,language,code,stdin,assignment_id, new SubmitCallback() {
			@Override
			public void onSuccess(SubmitResponse submitResponse) {
				compilerView.showOutput(submitResponse.getMessage());
//				compilerView.showRunTime(compilerResponse.getTime());
//				compilerView.showError(compilerResponse.getErrors());
                compilerView.showLoader(false);

			}

			@Override
			public void onFailure() {
				compilerView.showError("Unable to connect to server. . . .");
				compilerView.showLoader(false);
			}
		});
	}
}
