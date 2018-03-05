package com.example.andriatae.mymapproject;/*
package com.example.andriatae.mymapproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.andriatae.mymapproject.Pojo.CarParkReservation;
import com.example.andriatae.mymapproject.R;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Andria TAE on 28/02/2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

        private Context mContext;
        private List<CarParkReservation> reservationList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, ID,startDateTime,Duration,Cost;


            public MyViewHolder(View view) {
                super(view);

                /*

                title = (TextView) view.findViewById(R.id.textView_name);
                ID = (TextView) view.findViewById(R.id.ID);
                startDateTime = view.findViewById(R.id.start_date2);
                Duration =view.findViewById(R.id.duration);
                Cost =view.findViewById(R.id.cost);
                */
            }
        }


        public RecycleAdapter(Context mContext, RealmResults<CarParkReservation> reservationList) {
            this.mContext = mContext;
            this.reservationList = reservationList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

           // View itemView = LayoutInflater.from(parent.getContext())
                  //  .inflate(R.layout.album_card, parent, false);

           // return new MyViewHolder(itemView);
       // }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            CarParkReservation reservation = reservationList.get(position);
            //set text for reservations

            holder.title.setText(reservation.getName());
            //holder.startDateTime.setText(reservation.g);


        }



        @Override
        public int getItemCount() {
            return reservationList.size();
        }
    }
*/