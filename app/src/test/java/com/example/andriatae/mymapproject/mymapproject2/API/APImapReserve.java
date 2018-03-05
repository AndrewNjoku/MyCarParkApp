package com.example.andriatae.mymapproject.mymapproject2.API;

import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.example.andriatae.mymapproject.Pojo.CarParkReservation;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Andria TAE on 03/03/2018.
 */

public interface APImapReserve {







    @POST("{id}/reserve")

    Call<CarParkReservation>sendCarParkForRes(@Path("id") int id, @Body CarParkReservation res);




            /*@Path("id") int id){
                           @Field("lat") String lat,
                           @Field("lng") String lng,
                           @Field("name") String nme,
                           @Field("cost_per_minute") String copm,
                           @Field("max_reserve_time_mins") Integer mrtm,
                           @Field("min_reserve_time_mins") Integer minrtm;
                           */







}
