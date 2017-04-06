package com.example.android.newsfeed;


public class News {


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    private String title;
    private String url;

    public News(String title, String url){
      this.title = title;
      this.url = url;

    }


}
