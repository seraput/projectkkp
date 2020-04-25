package com.android.projectnew.DataController.NotifUser;

public class DataItemAdmin {

    private String id, email, name, telepon, level;

    public DataItemAdmin() {
    }

    public DataItemAdmin(String id, String email, String name, String telepon, String level) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.telepon = telepon;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getLevel() {
        return level;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
