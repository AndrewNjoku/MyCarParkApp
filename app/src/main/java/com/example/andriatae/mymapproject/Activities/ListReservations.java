package com.example.andriatae.mymapproject.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andriatae.mymapproject.Adapter.Adapter;
import com.example.andriatae.mymapproject.Application.myApplication;
import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.example.andriatae.mymapproject.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListReservations extends AppCompatActivity {
    private RealmResults countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_bro);
        TabLayout Tab_Layout2 = findViewById(R.id.tabLayout3);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        Realm realm2=Realm.getInstance(myApplication.realmConfiguration2);

        realm2.beginTransaction();

        ArrayList<CarPark> list = new ArrayList(realm2.where(CarPark.class).findAll());
        //RealmResults<CarPark> resultss=

        realm2.commitTransaction();



        Tab_Layout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                 @Override
                                                 public void onTabSelected(TabLayout.Tab tab) {
                                                     switch (tab.getPosition()) {
                                                         case 0:

                                                             System.out.println("clicked on 0 tab");
                                                             Intent myIntentgoback = new Intent(ListReservations.this,MapsActivity.class);
                                                           startActivity(myIntentgoback);
                                                             break;

                                                         case 1:

                                                             System.out.println("clicked on two");
                                                             //Intent myIntent = new Intent(MapsActivity.this, ListReservations.class);

                                                           //  startActivity(myIntent);
                                                             break;

                                                     }

                                                 }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        RecyclerView.Adapter adapter = new Adapter(list);

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onResume(){
        super.onResume();



    }
}
