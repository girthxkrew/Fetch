package com.android.fetch.Network;

import com.android.fetch.Models.Results;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface FetchAPI {

    @GET("/hiring.json")
    Observable<List<Results>> getResults();
}
