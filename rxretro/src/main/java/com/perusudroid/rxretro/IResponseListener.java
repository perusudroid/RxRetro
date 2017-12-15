package com.perusudroid.rxretro;

/**
 * Created by perusu on 24/9/17.
 */

public interface IResponseListener extends IBaseResponseListener {

    //success response
    void onSuccess(String str, int requestId);

}
