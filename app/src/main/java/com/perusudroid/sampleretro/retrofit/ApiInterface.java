package com.perusudroid.sampleretro.retrofit;

import com.perusudroid.sampleretro.dto.request.PostInput;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import rx.Observable;

/**
 * Created by perusu on 13/5/17.
 */

public interface ApiInterface {


    @GET("index.php?apicall=getDish")
    Observable<Response<ResponseBody>> getData();

    @POST("index.php?apicall=postDish")
    Observable<Response<ResponseBody>> postData(@Body PostInput postInput);

    @PUT("index.php?apicall=updateDish")
    Observable<Response<ResponseBody>> updateData();

    @DELETE("index.php?apicall=deleteDish")
    Observable<Response<ResponseBody>> deleteData();

}
