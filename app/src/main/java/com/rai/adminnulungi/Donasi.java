package com.rai.adminnulungi;

public class Donasi {

    private String idbarang, nama,kategori,metode,tujuan, urlbarang,tgldonasi,statusdonasi, iddonatur, idpenerima,namadonatur;

    public Donasi(){

    }

    public Donasi(String idbarang, String nama, String kategori, String metode, String tujuan, String urlbarang, String tgldonasi, String statusdonasi, String iddonatur, String idpenerima, String namadonatur) {
        this.idbarang = idbarang;
        this.nama = nama;
        this.kategori = kategori;
        this.metode = metode;
        this.tujuan = tujuan;
        this.urlbarang = urlbarang;
        this.tgldonasi = tgldonasi;
        this.statusdonasi = statusdonasi;
        this.iddonatur = iddonatur;
        this.idpenerima = idpenerima;
        this.namadonatur = namadonatur;
    }

    public String getIdbarang() {
        return idbarang;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getMetode() {
        return metode;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getUrlbarang() {
        return urlbarang;
    }

    public String getTgldonasi() {
        return tgldonasi;
    }

    public String getStatusdonasi() {
        return statusdonasi;
    }

    public String getIddonatur() {
        return iddonatur;
    }

    public String getIdpenerima() {
        return idpenerima;
    }

    public String getNamadonatur() {
        return namadonatur;
    }
}
