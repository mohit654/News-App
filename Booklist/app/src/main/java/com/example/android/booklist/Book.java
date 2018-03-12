package com.example.android.booklist;

/**
 * Created by user on 13-06-2017.
 */

public class Book {
    private String imageResource;
    private String titleResource;
    private String authorResource;
    private String date;
    private String urlResource;
    public Book(String image_resource,String title,String author,String date_resource,String url){
        imageResource = image_resource;
        titleResource = title;
        authorResource = author;
        date = date_resource;
        urlResource = url;
    }
    public String getImageResource(){
        return imageResource;
    }
    public String getTitleResource(){
        return titleResource;
    }
    public String getAuthorResource(){
        return authorResource;
    }
    public String getDate(){
        return date;
    }
    public String getUrl(){return urlResource;}
}
