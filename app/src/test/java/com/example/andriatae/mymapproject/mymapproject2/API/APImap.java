package com.example.andriatae.mymapproject.mymapproject2.API;

import com.example.andriatae.mymapproject.Pojo.CarPark;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Andria TAE on 28/02/2018.
 */

public interface APImap {


    //api for accessing all cr parks in san francisco

    @GET("search")

    Observable<List<CarPark>>listCarpark(@Query("lat") String param1,
                                         @Query("lng") String param2);





}
