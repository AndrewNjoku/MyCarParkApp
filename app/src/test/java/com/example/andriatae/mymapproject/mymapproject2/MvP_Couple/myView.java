package com.example.andriatae.mymapproject.mymapproject2.MvP_Couple;

import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Andria TAE on 01/03/2018.
 */

public interface myView {



   void createMarkers(RealmResults<CarPark> rr);


    void sendToActivity(List<LatLng> l);

}
