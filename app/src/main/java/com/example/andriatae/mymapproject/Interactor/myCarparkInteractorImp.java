


package com.example.andriatae.mymapproject.Interactor;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;

import com.example.andriatae.mymapproject.API.APImap;
import com.example.andriatae.mymapproject.API.APImapReserve;
import com.example.andriatae.mymapproject.API.getNearestAPI;
import com.example.andriatae.mymapproject.Application.myApplication;
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
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
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

public class myCarparkInteractorImp implements myCarparkInteractor {

    //public static final String BASE_URL= "http://ridecellparking.herokuapp.com/api/v1/parkinglocations/";


    myApplication myAplication=myApplication.get();


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


        myfranapi = retrofit.create(APImap.class);
        myfranapi.listCarpark(lat,lng).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<CarPark>>() {
            @Override
            public void accept(List<CarPark> carParks) throws Exception {
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

    public void sendReservedtoApi(final CarPark cp) {

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


                    RealmConfiguration myconfig=myApplication.realmConfiguration2;

                    Realm realm2=Realm.getInstance(myconfig);


                    realm2.beginTransaction();

                    realm2.copyToRealm(cp);

                    RealmResults<CarPark> results=realm2.where(CarPark.class).findAll();

                    realm2.commitTransaction();

                    System.out.println("commiting the transaction!!!");

                    for (CarPark c:results) {

                        System.out.println("testing realm 2" +c.getName());
                    }






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
