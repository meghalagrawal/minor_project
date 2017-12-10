package sharedcode.turboeditor.compiler.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import sharedcode.turboeditor.Urls;
import sharedcode.turboeditor.compiler.data.CompilerRequest;
import sharedcode.turboeditor.compiler.data.CompilerResponse;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerApi {
	@POST(Urls.SUB_URL_COMPILER)
	Call<CompilerResponse>
	compileCode(@Body CompilerRequest compilerRequest);
}
