package com.rai.adminnulungi;

public class Profil {

    private String userId, namaUser, emailUser, noKTP, nomorHP,jeniskelamin,alamat, kota;

    public Profil() {
    }

    public Profil(String userId, String namaUser, String emailUser, String noKTP, String nomorHP, String jeniskelamin, String alamat, String kota) {
        this.userId = userId;
        this.namaUser = namaUser;
        this.emailUser = emailUser;
        this.noKTP = noKTP;
        this.nomorHP = nomorHP;
        this.jeniskelamin = jeniskelamin;
        this.alamat = alamat;
        this.kota = kota;
    }

    public String getUserId() {
        return userId;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public String getNoKTP() {
        return noKTP;
    }

    public String getNomorHP() {
        return nomorHP;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKota() {
        return kota;
    }
}
