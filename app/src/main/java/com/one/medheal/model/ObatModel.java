package com.one.medheal.model;

import java.io.Serializable;

public class ObatModel implements Serializable {

    private String namaObat;
    private String harga;
    private String kategori;
    private String indikasi;
    private String kontra;
    private String efek;
    private boolean favorite;
    private int gambarObat;

    public ObatModel(String namaObat, String harga, String kategori, String indikasi, String kontra, String efek, boolean fav, int gambarObat){
        this.namaObat = namaObat;
        this.harga = harga;
        this.kategori = kategori;
        this.indikasi = indikasi;
        this.kontra = kontra;
        this.efek = efek;
        this.favorite = fav;
        this.gambarObat = gambarObat;
    }

    public String getNamaObat(){
        return this.namaObat;
    }

    public String getHarga(){
        return this.harga;
    }

    public String getKategori(){
        return this.kategori;
    }

    public String getIndikasi(){
        return this.indikasi;
    }

    public String getKontra(){
        return this.kontra;
    }

    public String getEfek(){
        return this.efek;
    }

    public int getGambarObat(){
        return this.gambarObat;
    }

    public boolean getFav(){
        return this.favorite;
    }

    public void addFav(boolean fav){
        this.favorite = fav;
    }

}
