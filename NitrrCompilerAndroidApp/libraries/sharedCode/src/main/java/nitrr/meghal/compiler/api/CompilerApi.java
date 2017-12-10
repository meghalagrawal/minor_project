package nitrr.meghal.compiler.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import nitrr.meghal.Urls;
import nitrr.meghal.compiler.data.CompilerRequest;
import nitrr.meghal.compiler.data.CompilerResponse;

/**
 * Created by meghal on 4/12/17.
 */

public interface CompilerApi {
	@POST(Urls.SUB_URL_COMPILER)
	Call<CompilerResponse>
	compileCode(@Body CompilerRequest compilerRequest);
}
