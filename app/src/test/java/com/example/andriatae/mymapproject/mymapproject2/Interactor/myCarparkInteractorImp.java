package com.example.andriatae.mymapproject.mymapproject2.Interactor;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;

import com.example.andriatae.mymapproject.API.APImap;
import com.example.andriatae.mymapproject.API.APImapReserve;
import com.example.andriatae.mymapproject.API.getNearestAPI;
import com.example.andriatae.mymapproject.Interactor.*;
import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.example.andriatae.mymapproject.Pojo.CarParkReservation;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
//impo//rt retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

/**
 * Created by Andria TAE on 02/03/2018.
 */

public class myCarparkInteractorImp implements com.example.andriatae.mymapproject.Interactor.myCarparkInteractor {

    //public static final String BASE_URL= "http://ridecellparking.herokuapp.com/api/v1/parkinglocations/";


    APImap myfranapi;
    APImapReserve myReservation;
    Retrofit retrofit;

    public myCarparkInteractorImp() {

        retrofit = new Retrofit.Builder()
                .baseUrl("http://ridecellparking.herokuapp.com/api/v1/parkinglocations/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }


    @Override
    public void getCarparkstoshow(final myDatabaseInteractorImp dp,String lat,String lng) {


        System.out.println("getting car parks to show");


        myfranapi = retrofit.create(APImap.class);
        myfranapi.listCarpark(lat,lng).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<CarPark>>() {
            @Override
            public void accept(List<CarPark> carParks) throws Exception {

                if (carParks.size()<0) {
                    System.out.println("this is where the error lies");
                }
/*
           for (CarPark c : carParks){

               dp.getCarParkInRealm(c);
           }


*/
                dp.getCarParkInRealm(carParks);


            }
        });


    }

    @Override
    public void reserveCarPark(int id) {

    }

    public void sendReservedtoApi(CarPark cp) {

        CarParkReservation carParkToSend=new CarParkReservation();

        carParkToSend.setLat(cp.getLat());
        carParkToSend.setLng(cp.getLng());
        carParkToSend.setName(cp.getName());
        carParkToSend.setCostPerMinute(cp.getCostPerMinute());
        carParkToSend.setMaxReserveTimeMins(cp.getMaxReserveTimeMins());
        carParkToSend.setMinReserveTimeMins(cp.getMinReserveTimeMins());
        carParkToSend.setIsReserved(cp.getIsReserved());
        carParkToSend.setReservedUntil(cp.getReservedUntil());

        System.out.println(carParkToSend.toString());




        System.out.println("this is the carPark im sending!!!!!!"+ cp.toString());

        System.out.println("RESERVING NOW!!!");

        myReservation = retrofit.create(APImapReserve.class);

        myReservation.sendCarParkForRes(cp.getId(), carParkToSend).enqueue(new Callback<CarParkReservation>() {
            @Override
            public void onResponse(Call<CarParkReservation> call, Response<CarParkReservation> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());

                    Log.i(TAG, "post submitted to API." + response.body().toString());

                    System.out.println("Succcess?");
                }


            }

            @Override
            public void onFailure(Call<CarParkReservation> call, Throwable t) {

                Log.e(TAG, "Unable to submit post to API.");

               System.out.print("this is the error" + t.getMessage());

                System.out.println("Failure?");

            }

            public void showResponse(String response) {

                System.out.println(response);
            }
        });

        System.out.println("FINISHED RESERVING");





    }







    /*

    @Override
    public void reserveCarPark(int id, CarPark carPark){

        APImapReserve reserve=retrofit.create(APImapReserve.class);
       reserve.getForObserved(id,)

        mycall.






    }
    */


    public void bookCarPark(int ID) {

        /*








    }

    */


    }
}
