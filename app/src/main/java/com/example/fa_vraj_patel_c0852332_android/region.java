package com.example.fa_vraj_patel_c0852332_android;

public class region {
    private int id;
    private String reglatitude;
    private String reglongitude;
    private String title;

    public region() {
    }

    public region(int id, String reglatitude, String reglongitude, String title) {
        this.id = id;
        this.reglatitude = reglatitude;
        this.reglongitude = reglongitude;
        this.title = title;
    }

    public region(String reglatitude, String reglongitude, String title) {
        this.reglatitude = reglatitude;
        this.reglongitude = reglongitude;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReglatitude() {
        return reglatitude;
    }

    public void setReglatitude(String reglatitude) {
        this.reglatitude = reglatitude;
    }

    public String getReglongitude() {
        return reglongitude;
    }

    public void setReglongitude(String reglongitude) {
        this.reglongitude = reglongitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
