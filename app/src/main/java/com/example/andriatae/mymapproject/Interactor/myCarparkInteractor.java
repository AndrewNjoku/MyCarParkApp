package com.example.andriatae.mymapproject.Interactor;

import android.database.Observable;

import com.example.andriatae.mymapproject.Pojo.CarPark;

import retrofit2.Retrofit;

/**
 * Created by Andria TAE on 02/03/2018.
 */

public interface myCarparkInteractor {





  //  Observable<CarPark> getTopRatedMovies(String apiKey);

  void getCarparkstoshow(final myDatabaseInteractorImp dp,String d, String p);
  void reserveCarPark(int id);

  void sendReservedtoApi(CarPark cp);


}
