package com.example.andriatae.mymapproject.Interactor;

import com.example.andriatae.mymapproject.Pojo.CarPark;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Andria TAE on 03/03/2018.
 */



public class myDatabaseInteractorImp implements  myDatabaseInteractor {
    Realm realm;

    RealmResults<CarPark> storedFran;


    public void getCarParkInRealm(final CarPark c) {

        realm = null;

        try {
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {


                @Override
                public void execute(Realm realm) {

                    // copy the objects to realm Asynchronously
                    CarPark myAddition = realm.copyToRealmOrUpdate(c);


                }
            });
        } finally {
            if (!realm.equals(null)) {

                realm.close();
            }
        }
    }

    //incase i want to add a list of carparks instead of individually

    public void getCarParkInRealm(final List<CarPark> myCarPark) {

        realm = null;
        try {
            realm = Realm.getDefaultInstance();


            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    List<CarPark> myAddition;

                    myAddition = realm.copyToRealmOrUpdate(myCarPark);


                 

                    for(CarPark c:myAddition){

                        System.out.println("car parks in realm"+c.getName());
                    }


                }
            });
        } finally {


            if (!realm.equals(null)) {

                realm.close();
                System.out.println("realm  not null, closing");
            }
        }


    }

    public RealmResults printRealm() {


        realm = null;
        try {
            realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            RealmQuery<CarPark> query1 = realm.where(CarPark.class);


            storedFran = query1.findAllAsync();

            realm.commitTransaction();
        } finally {
            if (!realm.equals(null)) {
                realm.close();
            }
        }


        return storedFran;


    }

    public CarPark getCarParkforchange(String name,Calendar start, Calendar End)
    {

        CarPark myResult;
        CarPark myResultDeleted;
        CarPark savedResult;

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();



        RealmQuery<CarPark> query1 = realm.where(CarPark.class).and().equalTo("name",name);

        myResult = query1.findFirst();

        System.out.println("this is the match found"+myResult.getName()+"the ID is"+myResult.getId());

       // myResult.deleteFromRealm();

       // RealmQuery<CarPark> query2 = realm.where(CarPark.class).and().equalTo("name",name);


        //myResultDeleted=query2.findFirst();



        realm.commitTransaction();

//        System.out.println("this is the match found"+myResultDeleted.getName()+"the ID is"+myResultDeleted.getId());



            return  myResult;






    }

     public void sendReservedtoApi(CarPark cp){





    }
}
