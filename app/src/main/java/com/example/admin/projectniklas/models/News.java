package com.example.admin.projectniklas.models;


import java.util.Date;


public class News implements Comparable<News> {


    private String web;
    private String channel;
    private String news;
    private Date date;
    private String logo;

    public News(String web, String channel, String news, Date date, String logo) {
        this.web = web;
        this.channel = channel;
        this.news = news;
        this.date = date;
        this.logo = logo;
    }

    public String getWeb() {
        return web;
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

    @Override
    public int compareTo(News news) {
        return news.getDate().compareTo(getDate());
    }
}


