package com.one.medheal.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ObatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertObat(Obat obat);

    @Query("SELECT * FROM tObat")
    List<Obat> getAllObat();

    @Query("UPDATE tObat SET fav_obat=:fav WHERE id_obat = :id")
    void updateFavObat(boolean fav, String id);

}
