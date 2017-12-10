package sharedcode.turboeditor.compiler;


import sharedcode.turboeditor.compiler.data.CompilerResponse;

/**
 * Created by meghal on 17/10/17.
 */

public interface CompilerCallback {
    void onSuccess(CompilerResponse compilerResponse);
    void onFailure();
}
