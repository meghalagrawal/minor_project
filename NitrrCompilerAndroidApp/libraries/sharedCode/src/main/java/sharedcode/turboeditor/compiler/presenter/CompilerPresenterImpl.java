package sharedcode.turboeditor.compiler.presenter;

import sharedcode.turboeditor.compiler.CompilerCallback;
import sharedcode.turboeditor.compiler.data.CompilerResponse;
import sharedcode.turboeditor.compiler.model.CompilerHelper;
import sharedcode.turboeditor.compiler.view.CompilerView;

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
				String error= compilerResponse.getErrors().toString();

				if(error.length()==0){
					error = "No Error";
				}


				compilerView.showOutput("Output is "+compilerResponse.getOutput()+"\n"+"Time to compile "+compilerResponse.getTime()+"\n Error "+error);
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
}
