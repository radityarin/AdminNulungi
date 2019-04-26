package com.rai.adminnulungi;

public class Tempat {
    private String iduser, email, alamat, kategori, nama, notelepon, urlfoto, kebutuhan, kordinat;

    public Tempat(){

    }

    public Tempat(String iduser, String email, String alamat, String kategori, String nama, String notelepon, String urlfoto, String kebutuhan, String kordinat) {
        this.iduser = iduser;
        this.email = email;
        this.alamat = alamat;
        this.kategori = kategori;
        this.nama = nama;
        this.notelepon = notelepon;
        this.urlfoto = urlfoto;
        this.kebutuhan = kebutuhan;
        this.kordinat = kordinat;
    }

    public String getIduser() {
        return iduser;
    }

    public String getEmail() {
        return email;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKategori() {
        return kategori;
    }

    public String getNama() {
        return nama;
    }

    public String getNotelepon() {
        return notelepon;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public String getKebutuhan() {
        return kebutuhan;
    }

    public String getKordinat() {
        return kordinat;
    }
}
