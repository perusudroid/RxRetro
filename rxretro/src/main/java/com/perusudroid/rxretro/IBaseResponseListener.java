package com.perusudroid.rxretro;

/**
 * Created by perusu on 24/9/17.
 */

public interface IBaseResponseListener {

    //apart from api call error, server communication errors
    void serverError(String stringFromByte, int paramInt, int errorCode);

    //api call failure response
    void onFailure(Throwable t, int requestId);
}
