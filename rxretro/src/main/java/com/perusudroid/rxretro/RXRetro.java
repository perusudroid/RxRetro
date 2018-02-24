package com.perusudroid.rxretro;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorFailedException;
import rx.schedulers.Schedulers;

/**
 * Created by perusu on 24/9/17.
 */

public class RXRetro {

    private static String TAG = RXRetro.class.getSimpleName();

    private static RXRetro rxRetro;

    public static RXRetro getInstance() {
        if (rxRetro == null) {
            rxRetro = new RXRetro();
        }
        return rxRetro;
    }


    private String getStringFromByte(InputStream paramInputStream) {
        StringBuilder localStringBuilder = new StringBuilder();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
        try {
            while (true) {
                String str = localBufferedReader.readLine();
                if (str == null)
                    break;
                localStringBuilder.append(str);
            }
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
        return localStringBuilder.toString();
    }


    /**
     * @param call              - retrofit request call
     * @param iResponseListener - Response will be send to the classes where iResponseListener is implemented
     * @param requestId         - type Id of each request
     *                          - multiple api call from one activity can be handled with different requestId
     */

    public void retrofitEnque(Observable<Response<ResponseBody>> call, final IResponseListener iResponseListener, final int requestId) {

        call
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<ResponseBody>>() {
                    @Override
                    public void onCompleted() {
                        if (iResponseListener != null) {
                            iResponseListener.onSuccess("Completed", requestId);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {

                            if (e instanceof OnErrorFailedException) {
                                Log.e(TAG, "onError: instanceof OnErrorFailedException");
                            } else if (e instanceof HttpException) {
                                Log.e(TAG, "onError: instanceof HttpException");
                                ResponseBody responseBody = ((HttpException) e).response().errorBody();
                                if (responseBody != null) {
                                    String errorStr = getStringFromByte(getByteStream(responseBody));
                                    Log.e(TAG, "onError: error msg" + errorStr);
                                }
                            } else {
                                if (iResponseListener != null) {
                                    iResponseListener.onFailure(e, requestId);
                                }
                            }
                        } catch (Exception ex) {
                            Log.e(TAG, "onError: "+ e.getLocalizedMessage() );
                        }

                    }

                    @Override
                    public void onNext(Response<ResponseBody> paramResponse) {

                        Log.d(TAG, "RESPONSE CODE - " + paramResponse.raw().code());
                        Log.d(TAG, "RAW RESPONSE - " + paramResponse.raw());

                        Object localObject = paramResponse.body();
                        String str = null;
                        if (localObject != null) {
                            str = getStringFromByte(getByteStream(paramResponse.body()));
                            Log.d(TAG, "=" + str);
                        }

                        switch (paramResponse.code()) {
                            case NetworkCodes.httpcodes.STATUS_OK:
                                iResponseListener.onSuccess(str, requestId);
                                break;
                            case NetworkCodes.httpcodes.STATUS_CREATED:
                                iResponseListener.onSuccess(str, requestId);
                                break;
                            case NetworkCodes.httpcodes.STATUS_BAD_REQUEST:
                                iResponseListener.serverError(getStringFromByte(getByteStream(paramResponse.errorBody())), requestId, NetworkCodes.httpcodes.STATUS_BAD_REQUEST);
                                break;
                            case NetworkCodes.httpcodes.STATUS_UNAUTHORIZED:
                                iResponseListener.serverError(getStringFromByte(getByteStream(paramResponse.errorBody())), requestId, NetworkCodes.httpcodes.STATUS_UNAUTHORIZED);
                                break;
                            case NetworkCodes.httpcodes.STATUS_FORBITTEN:
                                iResponseListener.serverError(paramResponse.raw().message(), requestId, NetworkCodes.httpcodes.STATUS_FORBITTEN);
                                break;
                            case NetworkCodes.httpcodes.STATUS_NOT_FOUND:
                                iResponseListener.serverError(paramResponse.raw().message(), requestId, NetworkCodes.httpcodes.STATUS_NOT_FOUND);
                                break;
                            case NetworkCodes.httpcodes.STATUS_SERVER_ERROR:
                                iResponseListener.serverError(getStringFromByte(getByteStream(paramResponse.errorBody())), requestId, NetworkCodes.httpcodes.STATUS_SERVER_ERROR);
                                break;
                            case NetworkCodes.httpcodes.STATUS_NO_CONTENT:
                                iResponseListener.serverError(getStringFromByte(getByteStream(paramResponse.errorBody())), requestId, NetworkCodes.httpcodes.STATUS_NO_CONTENT);
                                break;
                        }

                    }

                });

    }

    private InputStream getByteStream(ResponseBody paramResponse) {
        return paramResponse.byteStream();
    }

}
