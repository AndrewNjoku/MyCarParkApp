package com.example.andriatae.mymapproject.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andriatae.mymapproject.Application.myApplication;
import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.example.andriatae.mymapproject.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Andria TAE on 05/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private RealmResults<CarPark> countries;

        public Adapter(ArrayList countries)
        {
            Realm realm2=Realm.getInstance(myApplication.realmConfiguration2);

            realm2.beginTransaction();
            RealmResults<CarPark>resultss=realm2.where(CarPark.class).findAll();

            realm2.commitTransaction();

            for(CarPark p:resultss) {


                System.out.println("this is the major test" + p.getName());
            }


            this.countries=resultss;
        }

        @Override
        public Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(Adapter.ViewHolder viewHolder, int i) {

            viewHolder.name.setText(countries.get(i).getName());

            viewHolder.id.setText(countries.get(i).getId().toString());

            viewHolder.reserved_until.setText(countries.get(i).getReservedUntil());
        }

        @Override
        public int getItemCount() {
            return countries.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView name;

            private TextView id;

            private TextView reserved_until;

            public ViewHolder(View view) {
                super(view);

                name = (TextView)view.findViewById(R.id.name);
                id = (TextView)view.findViewById(R.id.identification);
                reserved_until = (TextView)view.findViewById(R.id.price);
            }
        }

    }

