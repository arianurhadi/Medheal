package com.one.medheal.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tUser")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_user")
    private String idUser;

    @ColumnInfo(name = "nama_user")
    private String namaUser;

    @ColumnInfo(name = "email_user")
    private String emailUser;

    User(@NonNull String idUser, String namaUser, String emailUser){
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.emailUser = emailUser;
    }

    public String getIdUser(){return this.idUser;}
    public String getNamaUser(){return this.namaUser;}
    public String getEmailUser(){return this.emailUser;}


}
