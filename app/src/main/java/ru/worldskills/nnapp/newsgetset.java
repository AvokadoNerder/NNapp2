package ru.worldskills.nnapp;

public class newsgetset {

    private String Header;
    private String Text;
    private String Date;
    private String Image;

    public newsgetset() {
    }

    public newsgetset(String head, String text, String data, String image) {
        Header = head;
        Text = text;
        Date = data;
        Image = image;
    }

    public String getHeader() {
        return Header;
    }

    public void setHeader(String header) {
        Header = header;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
