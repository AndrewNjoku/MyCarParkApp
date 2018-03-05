package com.example.andriatae.mymapproject.API;

import com.example.andriatae.mymapproject.Pojo.CarPark;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Andria TAE on 04/03/2018.
 */

public interface APIinfoview {


    public interface APImap {


        //api for accessing all cr parks in san francisco

        @GET("{id}")

        Observable<CarPark> listCarpark(@Path("id") int id);





    }

}
