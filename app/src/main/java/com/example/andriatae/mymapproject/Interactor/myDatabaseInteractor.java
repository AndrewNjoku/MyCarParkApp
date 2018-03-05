package com.example.andriatae.mymapproject.Interactor;

import com.example.andriatae.mymapproject.Pojo.CarPark;

import java.util.List;

/**
 * Created by Andria TAE on 03/03/2018.
 */

public interface myDatabaseInteractor {



    void getCarParkInRealm (CarPark myCarPark);


    void getCarParkInRealm(List<CarPark> myCarPark);



}
