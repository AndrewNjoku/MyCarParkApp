package com.example.andriatae.mymapproject.mymapproject2.MvP_Couple;

import com.example.andriatae.mymapproject.Interactor.myCarparkInteractor;
import com.example.andriatae.mymapproject.Interactor.myCarparkInteractorImp;
import com.example.andriatae.mymapproject.Interactor.myDatabaseInteractorImp;
import com.example.andriatae.mymapproject.MvP_Couple.*;
import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andria TAE on 01/03/2018.
 */

public class myPresenter {

    Calendar myCalendar;

    //myCarparkInteractor interactor;
   final  myCarparkInteractorImp interactorC;
   final  myDatabaseInteractorImp interactorD;

  //implemented view
   com.example.andriatae.mymapproject.MvP_Couple.myViewImpl view;

   //Arraylist to hold car park co-ordinates;
    ArrayList<CarPark> arrayCFran;


    Retrofit retrofit;
    Realm realm;


    public myPresenter(com.example.andriatae.mymapproject.MvP_Couple.myViewImpl view, RealmConfiguration RealmConfig, String tag) {

        this.view = view;


        this.realm = Realm.getInstance(RealmConfig);

        interactorC = new myCarparkInteractorImp();

        interactorD = new myDatabaseInteractorImp();


        if (interactorC.equals(null)) {
            System.out.println("c interactor is null");
        }

        if (interactorD.equals(null)) {
            System.out.println(" D interactor is null");
        }

        //on creation of presenter i am loading up all the initial car park locations
        //

        if (tag.equals("CarPark")) {


        RealmResults<CarPark> results;
        //check if realm is empty
        realm.beginTransaction();
        RealmQuery query = realm.where(CarPark.class);
        results = query.findAll();
        realm.commitTransaction();

//        System.out.println("testing realm blah blah" + results.get(3).getName());


        if (results.isEmpty()) {

            System.out.println("is empty so im going to fill results");

            interactorC.getCarparkstoshow(interactorD, "18.36245", "-66.56128");

            // interactor.getFranCp();

        }
    }
    }


    public RealmResults<CarPark>PrintResults() {

        List<LatLng>finalLng;



       final  RealmResults<CarPark>myresults = interactorD.printRealm();

       System.out.println("is REALMRESULTS EMPTY? "+ myresults.get(0).getName());



        for (CarPark c : myresults) {

            System.out.println("these are the car parks stored in realm" + c.getName());
        }

        //view.createMarkers(myresults);

       // finalLng=view.getLatLongforprint();

        if(myresults.equals(null)){

            System.out.println("my results are null");
        }

        //if(finalLng.equals(null)){


         //   System.out.println("defiently this is the problem");
      //  }

        //System.out.println("i think this may be the problem"+finalLng.get(4).toString());



        return myresults;
    }


    public void pleaseReserve(String name, Calendar myStartTime,int Duration )
    {

        Realm realm= Realm.getDefaultInstance();


        Calendar start=myStartTime;
        //adding the duration of stay to the start date of reservation

        System.out.println(myStartTime.getTime().toString());
this.myCalendar=myStartTime;

myCalendar.add(Calendar.MINUTE,Duration);


        //get id from name







        final CarPark toreserve;

        toreserve=interactorD.getCarParkforchange(name,myStartTime,myCalendar);


        System.out.println("fabulous");

        System.out.println("this is the carPark to reserve"+toreserve.getName()+"id is"+toreserve.getId()+"This is the start of reservation"+myStartTime.getTime().toString()+"reserving for"+ Duration+"Minutes"+"End Date and Time"+myCalendar.getTime().toString());

        // now we have to initiate the API call and pass  this carpark to the server to book it using ID value

        //need to set some fields on car object before sending it on its way


        //test

        realm.beginTransaction();



        toreserve.setIsReserved(false);

        toreserve.setReservedUntil(myCalendar.getTime().toString());



        realm.commitTransaction();

        System.out.println("after change"+toreserve.getIsReserved()+"Reserved until"+toreserve.getReservedUntil());

       //send this objecct to api method



        interactorC.sendReservedtoApi(toreserve);















    }

    public void getIdFromName( Realm realm)
    {





    }

    /*

        public List<LatLng> ResultsToMarker(){

            final RealmResults<CarPark> myresults = interactorD.printRealm();




            myresults.addChangeListener(new RealmChangeListener<RealmResults<CarPark>>() {
                @Override
                public void onChange(RealmResults<CarPark> carParks) {
                    if (carParks.size() > 10) {

                        System.out.println(" inside presnter in results to marker the realmresults is bigger than 10 so copying to list to return");
                       List<LatLng> myLat = new ArrayList<>();

                        for (CarPark c : myresults) {

                            myLat.add(new LatLng(Double.parseDouble(c.getLat()), Double.parseDouble(c.getLng())));
                        }
                    }
                }
            });



    return myL;
    }
    */


/*

pub


    public void performCarParkSearch(String APIkey){


        interactor.getNearestFive()



    } */




}
