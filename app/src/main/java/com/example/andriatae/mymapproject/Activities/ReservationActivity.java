package com.example.andriatae.mymapproject.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.andriatae.mymapproject.Application.myApplication;
import com.example.andriatae.mymapproject.Interactor.myCarparkInteractorImp;
import com.example.andriatae.mymapproject.MvP_Couple.myPresenter;
import com.example.andriatae.mymapproject.MvP_Couple.myViewImpl;
import com.example.andriatae.mymapproject.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ReservationActivity extends AppCompatActivity {


    myPresenter reservationPresenter;
    myViewImpl viewRes;
    myApplication application;

    Calendar myStartDate;

    //Duration
    int mySpinnerValue;

    //name of CarPark
    String name;




    DatePicker myDatePicker;
    TextView myName;
    TextView andAnotherOne;
    Button myResButton;
    Button myResButtonTime;
    Button myResButtonDuration;
    Spinner mySpinner;
    Integer[] arraySpinner;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        application=myApplication.get();


        arraySpinner = new Integer[]{
                20, 40, 60, 80, 100, 120
        };

        viewRes=new myViewImpl();

        reservationPresenter=new myPresenter(viewRes,application.getRealm("ResRealm"),"Reservation");


    }

    @Override
    protected void onStart() {
        super.onStart();

        myName = findViewById(R.id.name_view_r);

        Bundle extra = getIntent().getExtras();

         name = extra.getString("Name");

        andAnotherOne = findViewById(R.id.textView3);

        myName.setText("    Carpark:" + name);

        myDatePicker = findViewById(R.id.datePicker);


        myResButton = findViewById(R.id.button2res);

        myResButtonTime = findViewById(R.id.button2res2);

        myResButtonDuration=findViewById(R.id.button2res3);

        timePicker = findViewById(R.id.timePicker2);

        mySpinner = findViewById(R.id.spinnerr);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(adapter);





/*

        Calendar myCalender = Calendar.getInstance();
        int year = myCalender.get(Calendar.YEAR);
        int month = myCalender.get(Calendar.MONTH);
        int day = myCalender.get(Calendar.DAY_OF_MONTH);

        myDatePicker.setMinDate(myCalender.getTimeInMillis());
        */
        Calendar pickedDate = getDateFromDatePicker(myDatePicker);
        System.out.println("this is the date picked" + pickedDate.toString());


    }

    public static java.util.Calendar getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        datePicker.setMinDate(calendar.getTimeInMillis());
        calendar.set(year, month, day);

        return calendar;
    }

    public void setTimeAddtoDate() {
        int Hour = timePicker.getHour();
        int Minute = timePicker.getMinute();

        try {

            myStartDate.set(Calendar.HOUR_OF_DAY, Hour);
            myStartDate.set(Calendar.MINUTE, Minute);

        } catch (NullPointerException p) {


            System.out.println(p.getMessage());
        }

        System.out.println("hour" + Hour + "Minute" + Minute + "this is the calender object" + myStartDate.toString());


    }


    public void onCLickDate(View view) {


        switch (view.getId()) {


            case R.id.button2res:
                myStartDate = getDateFromDatePicker(myDatePicker);
                Toast.makeText(this, "Date Set", Toast.LENGTH_SHORT).show();
                myDatePicker.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);

                myResButton.setVisibility(View.INVISIBLE);
                myResButtonTime.setVisibility(View.VISIBLE);

                break;

            case R.id.button2res2:
                setTimeAddtoDate();
                timePicker.setVisibility(View.INVISIBLE);
                mySpinner.setVisibility(View.VISIBLE);
                andAnotherOne.setVisibility(View.VISIBLE);
                myResButtonDuration.setVisibility(View.VISIBLE);

                Toast.makeText(this, "Time Set", Toast.LENGTH_SHORT).show();


                break;

            case R.id.button2res3:
                //set the duration from

                mySpinnerValue=(Integer)mySpinner.getSelectedItem();


                System.out.println(mySpinnerValue);





                //test that it is getting spinner value
               // System.out.println("time in spinner is"+DurationStay);

                //testing start date and duration

                System.out.println("Reservation information Date info:"+myStartDate.getTime().toString()+"Duration"+mySpinnerValue);

                reservationPresenter.pleaseReserve(name,myStartDate,mySpinnerValue);


                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Reserving You a Spot!!");
                //builder.setMessage("After 2 second, this dialog will be closed automatically!");
                builder.setCancelable(true);

                final AlertDialog dlg = builder.create();

                dlg.show();

                final Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        dlg.dismiss(); // when the task active then close the dialog
                        t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
                    }
                }, 4000); // after 2 second (or 2000 miliseconds), the task will be active.

                Intent backtoMap=new Intent(this,MapsActivity.class);

                startActivity(backtoMap);

        }



                //reservationPresenter.pleaseReserve(name,myStartDate,mySpinnerValue,dialog);


            // System.out.println("this is the date picked"+pickedDate.toString(


        }

    }
