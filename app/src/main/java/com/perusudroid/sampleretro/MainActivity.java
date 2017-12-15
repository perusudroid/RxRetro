package com.perusudroid.sampleretro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.perusudroid.rxretro.IResponseListener;
import com.perusudroid.rxretro.RXRetro;
import com.perusudroid.sampleretro.dto.request.PostInput;
import com.perusudroid.sampleretro.dto.response.get.GetSample;
import com.perusudroid.sampleretro.dto.response.post.POSTResponse;
import com.perusudroid.sampleretro.retrofit.ApiClient;

public class MainActivity extends BaseActivity implements IResponseListener, View.OnClickListener{

    private Button btnPost, btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setAssets();
    }

    private void setAssets() {
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
    }

    private void bindViews() {
        btnPost = findViewById(R.id.btnPost);
        btnGet = findViewById(R.id.btnGet);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnGet:
                RXRetro.getInstance().retrofitEnque(ApiClient.getInterface().getData(), this, 1);
                break;
            case R.id.btnPost:
                PostInput postInput = new PostInput("Abc", "Android");
                RXRetro.getInstance().retrofitEnque(ApiClient.getInterface().postData(postInput), this, 2);
                break;
        }

    }


    /**
     * On SuccessfulApi Retrofit response will be directed to this method with {@param requestId} passed when calling api
     * @param str - response in String
     * @param requestId - requestId send while calling Api
     */

    @Override
    public void onSuccess(String str, int requestId) {
        switch (requestId) {
            case 1:
                GetSample getSample = getGson().fromJson(str, GetSample.class);
                showToast(getSample.getMessage());
                break;
            case 2:
                POSTResponse postResponse = getGson().fromJson(str, POSTResponse.class);
                showToast("Your name is "+ postResponse.getData().getName() + " And your hobby is "+ postResponse.getData().getHobby());
                break;
        }
    }

        /**
     *  This method already implemented in {@link BaseActivity}
     *  this is sample to override from {@link BaseActivity} and write our own function like dismiss progressbar
     * @param t - Server error message
     * @param requestId - Shows ID of the requested ApiCall
     */

    @Override
    public void onFailure(Throwable t, int requestId) {
        super.onFailure(t, requestId);
    }
}
