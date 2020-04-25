package com.android.projectnew.DataController.NotifUser;

public class DataItemUser {

    private String id, email, nama, nilai1, nilai2, nilai3, tanggal, status;

    public DataItemUser() {
    }

    public DataItemUser(String id, String email, String nama, String nilai1, String nilai2, String nilai3, String tanggal, String status) {
        this.id = id;
        this.email = email;
        this.nama = nama;
        this.nilai1 = nilai1;
        this.nilai2 = nilai2;
        this.nilai3 = nilai3;
        this.tanggal = tanggal;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getNilai1() {
        return nilai1;
    }

    public String getNilai2() {
        return nilai2;
    }

    public String getNilai3() {
        return nilai3;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNilai1(String nilai1) {
        this.nilai1 = nilai1;
    }

    public void setNilai2(String nilai2) {
        this.nilai2 = nilai2;
    }

    public void setNilai3(String nilai3) {
        this.nilai3 = nilai3;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
