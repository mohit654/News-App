package com.example.android.newsfeed;

/**
 * Created by user on 16-06-2017.
 */

public class News {
    private String mHeadline;
    private String mDescription;
    private String mAuthor;
    private String mTime;
    private String mImageResource;
    private String mUrl;
    public News(String headline,String description,String author,String time,String image_resource,String url){
        mHeadline = headline;
        mDescription = description;
        mAuthor = author;
        mTime = time;
        mImageResource = image_resource;
        mUrl = url;
    }
    public String getHeadline(){
        return mHeadline;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getAuthor(){return mAuthor;}
    public String getTime(){return mTime;}
    public String getImageResource(){return mImageResource;}
    public String getUrl(){return mUrl;}
}
