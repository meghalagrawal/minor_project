package nitrr.meghal.compiler;


import nitrr.meghal.compiler.data.SubmitResponse;

/**
 * Created by meghal on 17/10/17.
 */

public interface SubmitCallback {
    void onSuccess(SubmitResponse submitResponse);
    void onFailure();
}
