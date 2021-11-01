package com.one.medheal.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tObat")
public class Obat implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_obat")
    private String idObat;

    @ColumnInfo(name = "nama_obat")
    private String namaObat;

    @ColumnInfo(name = "harga_obat")
    private String hargaObat;

    @ColumnInfo(name = "kategori_obat")
    private String kategoriObat;

    @ColumnInfo(name = "indikasi_obat")
    private String indikasiObat;

    @ColumnInfo(name = "kontra_obat")
    private String kontraObat;

    @ColumnInfo(name = "efek_obat")
    private String efekObat;

    @ColumnInfo(name = "fav_obat")
    private boolean favObat;

    @ColumnInfo(name = "gambar_obat")
    private int gambarObat;


    public Obat(@NonNull String idObat, String namaObat, String hargaObat, String kategoriObat, String indikasiObat, String kontraObat, String efekObat, boolean favObat, int gambarObat){
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.hargaObat = hargaObat;
        this.kategoriObat = kategoriObat;
        this.indikasiObat = indikasiObat;
        this.kontraObat = kontraObat;
        this.efekObat = efekObat;
        this.favObat = favObat;
        this.gambarObat = gambarObat;
    }

    public String getIdObat(){
        return this.idObat;
    }

    public String getNamaObat(){
        return this.namaObat;
    }

    public String getHargaObat(){ return this.hargaObat;}

    public String getKategoriObat(){
        return this.kategoriObat;
    }

    public String getIndikasiObat(){
        return this.indikasiObat;
    }

    public String getKontraObat(){
        return this.kontraObat;
    }

    public String getEfekObat(){
        return this.efekObat;
    }

    public int getGambarObat(){
        return this.gambarObat;
    }

    public boolean getFavObat(){
        return this.favObat;
    }

    public void addFav(boolean fav){
        this.favObat = fav;
    }


}
