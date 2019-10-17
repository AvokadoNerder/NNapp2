package ru.worldskills.nnapp;

public class people_getset {

    String Image;
    String Name;
    String SurName;

    public people_getset() {
    }

    public people_getset(String image, String name, String surName) {
        Image = image;
        Name = name;
        SurName = surName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }
}
