package com.example.android.learn;

public class RowItem {
    private int imageId;
    private String title;
    private String desc;

    public RowItem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;

    }
    //placing the icons to single row
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    //placing the lesson name to single row
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}