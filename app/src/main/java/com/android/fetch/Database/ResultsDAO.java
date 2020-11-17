package com.android.fetch.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.fetch.Models.Results;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

@Dao
public interface ResultsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveResults(List<Results> results);

    @Query("SELECT * FROM results WHERE NOT IFNULL(LENGTH(name), 0) = 0 ORDER BY listId ASC, name ASC")
    Observable<List<Results>> getResults();
}
