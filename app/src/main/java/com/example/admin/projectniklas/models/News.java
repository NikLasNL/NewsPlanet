package com.example.admin.projectniklas.models;

import java.util.Date;

public class News {

    private String channel;
    private String news;
    private Date date;
    private String logo;

    public News(String channel, String news, Date date, String logo) {
        this.channel = channel;
        this.news = news;
        this.date = date;
        this.logo = logo;
    }

    public String getChannel() {
        return channel;
    }

    public String getNews() {
        return news;
    }

    public Date getDate() {
        return date;
    }

    public String getLogo() {
        return logo;
    }
}


