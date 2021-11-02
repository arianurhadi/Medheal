package com.one.medheal.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PengingatObatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPengingat(PengingatObat pengingatObat);

    @Query("SELECT * FROM tPengingatObat")
    List<PengingatObat> getAllPengingat();

    @Update
    void updatePengingat(PengingatObat pengingatObat);

}
