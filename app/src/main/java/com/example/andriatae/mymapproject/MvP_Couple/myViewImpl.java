package com.example.andriatae.mymapproject.MvP_Couple;

import android.app.Activity;
import android.view.View;

import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by Andria TAE on 03/03/2018.
 */

public class myViewImpl implements myView {

    public ArrayList<LatLng> thisBetterWork= new ArrayList<LatLng>();


    @Override
    public void createMarkers (RealmResults<CarPark> rr) {

        for (CarPark p : rr) {


            System.out.println("println test motherfucker" + p.getName());
        }

        //thisBetterWork=new ArrayList<>();

        rr.addChangeListener(new RealmChangeListener<RealmResults<CarPark>>() {
            @Override
            public void onChange(RealmResults<CarPark> carParks) {

                // System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");

                if (carParks.size() > 12) {

                    System.out.println(" inside viewImpl in results realmresults is bigger than 12 so copying to list to return");



                   // for(int i=0;i<carParks.size();i++)
                  //  {
                      //  thisBetterWork.add(i, new LatLng(Double.parseDouble(carParks.get(i).getLat()), Double.parseDouble(carParks.get(i).getLng())));
                  //  }

                   // System .out.println("the moment of truth"+thisBetterWork.size() );

                  //  for (CarPark c : carParks) {
                    //   thisBetterWork.add(new LatLng(Double.parseDouble(c.getLat()), Double.parseDouble(c.getLng())));


                    //  }
                    //  System.out.println("is it working here aswell"+thisBetterWork.get(0).toString());


                }


            }

        });
    }


    @Override
    public void sendToActivity(List<LatLng> l) {

    }

    List<LatLng> getLatLongforprint()
    {

        System.out.println("this is the major test here "+ thisBetterWork.size());


        return thisBetterWork;
    }



}
