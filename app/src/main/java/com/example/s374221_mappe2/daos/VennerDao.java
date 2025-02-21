package com.example.s374221_mappe2.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.s374221_mappe2.enteties.Venner;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface VennerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertVenn(Venner... venner);

    @Update
    Completable updateVenn(Venner venn);

    @Query("SELECT * FROM venner")
    Single<List<Venner>> getAll();

    @Query("SELECT * FROM venner WHERE id = :vennId") // Hent venn basert på ID
    Single<Venner> getVennById(int vennId);

    @Query("SELECT * FROM venner WHERE SUBSTR(foedselsdato, 4, 2) || '-' || SUBSTR(foedselsdato, 1, 2) = :dato")
    Single<List<Venner>> getFriendsWithBirthday(String dato);
    @Delete
    Completable delete(Venner venn);
}
