package com.perusudroid.sampleretro;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.perusudroid.rxretro.IBaseResponseListener;

/**
 * Created by perusu on 13/12/17.
 */


public class BaseActivity extends AppCompatActivity implements IBaseResponseListener {

    /**
     * BaseActivity implements IBaseResponseListener
     * Server error from the ResponseListener wil e handled globally - not to implement in every activity.
     * Activities should extend BaseActivity
     */

    private Gson mGson;

    protected Gson getGson(){
        if(mGson == null){
            mGson = new Gson();
        }
        return mGson;
    }

    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError(String serverResponse, int paramInt, int errorCode) {
        showToast(serverResponse + " ("+ errorCode + ") ");
    }

    /**
     * By implementing this method in BaseActivity will by default shows the server api call failure message
     * If needed , this method can be overridden in your own activity
     *
     * @param t - Server error message
     * @param requestId - Shows ID of the requested ApiCall
     */

    @Override
    public void onFailure(Throwable t, int requestId) {
        showToast(t.getLocalizedMessage());
    }
}
