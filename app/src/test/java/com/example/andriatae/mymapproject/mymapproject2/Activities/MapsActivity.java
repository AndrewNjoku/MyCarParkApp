package com.example.andriatae.mymapproject.mymapproject2.Activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andriatae.mymapproject.Activities.ReservationActivity;
import com.example.andriatae.mymapproject.Application.myApplication;
import com.example.andriatae.mymapproject.Interactor.myCarparkInteractor;
import com.example.andriatae.mymapproject.Model.MyFirebaseInstanceIDService;
import com.example.andriatae.mymapproject.MvP_Couple.myPresenter;
import com.example.andriatae.mymapproject.MvP_Couple.myViewImpl;
import com.example.andriatae.mymapproject.Pojo.CarPark;
import com.example.andriatae.mymapproject.R;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    myApplication application;
    private GoogleMap mMap;
    public Boolean mLocationPermissionGranted = true;
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;

    RealmResults <CarPark> myResults;

    public List<LatLng> myLatLng=new ArrayList<>();

    public myPresenter presenter;

    public myViewImpl view;

    public myCarparkInteractor interactor;

   //Tracker for google analytics
    public Tracker mTracker;


    //Realm stuff
    Realm realm;
    RealmConfiguration carPark;

    //Firebase services for push notifications and more
    MyFirebaseInstanceIDService service;

    ProgressDialog dialog;

    GoogleMap googleMap;


   // AnalyticsApplication application = (AnalyticsApplication) getApplication();

    //My widgets
    EditText search;
    Button button;
    FloatingActionButton button2;
    TabLayout Tab_Layout;
    TabLayout.Tab Reservationtab;
    TabLayout.Tab MapsTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



  application=myApplication.get();

        /*
        Tab_Layout.addOnTabSelectedListener
                (new TabLayout.OnTabSelectedListener() {
                                                @Override
                                                public void onTabSelected(TabLayout.Tab tab) {

                                                    switch (tab.getPosition()) {


                                                        case 0:
                                                            System.out.println("tab 0 been clicked");
                                                            break;

                                                        case 1:
                                                            System.out.println("tab 1 has been selected");
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

                                            */





        /*
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading Map");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();*/

        //google analytics

       // mTracker = application.getDefaultTracker();


        // needs to be done in butterknife


        //Instanciate the firebase service

        service = new  MyFirebaseInstanceIDService();
        service.onTokenRefresh();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);


        //some layout configurations




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    //carPark=Realm.getInstance();

        // create the markers


        view=new myViewImpl();


        presenter = new myPresenter(view,application.getRealm("default"),"CarPark");

        //oh boy inception


        //myLatLng=presenter.PrintResults();


       // catch (NullPointerException i){

        //    System.out.println(i.getMessage());
       // }






       // createMarkers;

       //Asynchronously get markers and display on the map

        //new getMarkersAndDisplay().execute(presenter);
       // myLatLng=presenter.ResultsToMarker();


        //myLatLng=presenter.ResultsToMarker();









    }




    public void createMarkers(){

       // LatLng start = new LatLng(lat,Long);
      //  System.out.println("the size of the array of coords "+ myLatLng.size());

            myResults = presenter.PrintResults();


        int i=0;

        double lat2=Double.parseDouble(myResults.get(5).getLat());
        double longi2=Double.parseDouble(myResults.get(5).getLng());
        LatLng lit2=new LatLng(lat2,longi2);


        for(CarPark l:myResults){



            double lat=Double.parseDouble(l.getLat());
            double longi=Double.parseDouble(l.getLng());
            LatLng lit=new LatLng(lat,longi);
            System.out.println("info on the carparks name:"+l.getName()+"is reserved?"+l.getIsReserved()+"cost"+l.getCostPerMinute()+"reserved until"+l.getReservedUntil());



           if (l.getIsReserved().equals(true))
            {
                Marker marker = mMap.addMarker(new MarkerOptions().position(lit)
                        .title(l.getName()).snippet("This Car Park is available \n<*> Reservation information:<*> \nMinimum Time  Maximum Time  Cost\n    "+l.getMinReserveTimeMins()+" Minues         "+l.getMaxReserveTimeMins()+ "Minutes     "+"$"+l.getCostPerMinute()+"")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).zIndex(i));
            }

            else{
             //  Marker marker = mMap.addMarker(new MarkerOptions().position(lit).title("Marker" + l
                   //    .toString() + i).snippet("snippet").get.zIndex(i));


           }

            System.out.println(l.getName()+"has been added to marker with a latLng of"+lit.toString());
           i++;
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat2, longi2), 12.0f));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(lit2));


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            mLocationPermissionGranted = true;

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
        @Override
        public void onRequestPermissionsResult(int requestCode,
        @NonNull String permissions[],
        @NonNull int[] grantResults) {
            mLocationPermissionGranted = false;
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        mLocationPermissionGranted = true;
                    }
                }
            }
            updateLocationUI();
        }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                // = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Tab_Layout = findViewById(R.id.tabLayout3);
        Tab_Layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                @Override
                                                public void onTabSelected(TabLayout.Tab tab) {
                                                    switch (tab.getPosition()) {
                                                        case 0:

                                                            System.out.println("clicked on 0 tab");

                                                            break;

                                                        case 1:

                                                            System.out.println("clicked on two");
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

        System.out.println("Tab layout test" + Tab_Layout.getId());






        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.windowlayoutxml, null);

                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();

                // Getting reference to the TextView to set latitude
                TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);

                // Getting reference to the TextView to set longitude
                TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                // Setting the latitude
               tvLat.setText(arg0.getTitle());
                // Setting the longitude

                tvLng.setText(arg0.getSnippet());

                // Returning the view containing InfoWindow contents
                return v;

            }
        });

        updateLocationUI();






        //mMap.moveCamera(CameraUpdateFactory.newLatLng(start));


        //getDeviceLocation();

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
      //

        //Starting coordinates



        /*
        Double lat = new Double(myLatLng.get(1).latitude);
        Double Long = new Double(myLatLng.get(1).longitude);



        LatLng start = new LatLng(lat,Long);
        createMarkers(myLatLng);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(start));
*/

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

              String name =marker.getTitle();

                System.out.println("this is the name of the clicked info"+name);




               Intent Reservation = new Intent(MapsActivity.this,ReservationActivity.class);
               Reservation.putExtra("Name",marker.getTitle());
               startActivity(Reservation);



            }
        });

        for(int i=0;i<200;i++)
        {
            int c=5+5;
            c++;
        }

        createMarkers();


    }







       // createMarkers(myLatLng);



    /*

    private class getMarkersAndDisplay extends AsyncTask<myPresenter, Void, Void> {

        protected Void doInBackground(myPresenter... presenters) {

           // myLatLng=presenter.ResultsToMarker();

            return null;
        }

        protected void onProgressUpdate(Integer... progress) {

            createMarkers(myLatLng);

        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");

           // dialog.hide();


        }
    }

    */

}
