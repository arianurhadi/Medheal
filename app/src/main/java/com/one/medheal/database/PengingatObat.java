package com.one.medheal.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tPengingatObat")
public class PengingatObat implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_pengingat")
    private int idPengingat;

    @ColumnInfo(name = "nama_obat")
    private String namaObat;

    @ColumnInfo(name = "waktu")
    private String waktu;

    @ColumnInfo(name = "frekuensi")
    private String frekuensi;

    @ColumnInfo(name = "inventaris")
    private String inventaris;

    @ColumnInfo(name = "durasi")
    private String durasi;

    @ColumnInfo(name = "catatan")
    private String catatan;

    @ColumnInfo(name = "status")
    private boolean status;

    public PengingatObat(int idPengingat, String namaObat, String waktu, String frekuensi, String inventaris, String durasi, String catatan, boolean status){
        this.idPengingat = idPengingat;
        this.namaObat = namaObat;
        this.waktu = waktu;
        this.frekuensi = frekuensi;
        this.inventaris = inventaris;
        this.durasi = durasi;
        this.catatan = catatan;
        this.status = status;
    }

    @Ignore
    public PengingatObat(String namaObat, String waktu, String frekuensi, String inventaris, String durasi, String catatan, boolean status){
        this.namaObat = namaObat;
        this.waktu = waktu;
        this.frekuensi = frekuensi;
        this.inventaris = inventaris;
        this.durasi = durasi;
        this.catatan = catatan;
        this.status = status;
    }

    public int getIdPengingat() {return this.idPengingat;}
    public String getNamaObat() {return this.namaObat;}
    public String getFrekuensi() {return this.frekuensi;}
    public String getInventaris() {return this.inventaris;}
    public String getWaktu() {return this.waktu;}
    public String getDurasi() {return this.durasi;}
    public String getCatatan() {return this.catatan;}
    public boolean getStatus() {return this.status;}
}
