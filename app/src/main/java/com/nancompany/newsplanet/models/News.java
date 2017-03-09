package com.nancompany.newsplanet.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(active = true, nameInDb = "NEWS")
public class News implements Comparable<News> {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String web;
    @NotNull
    private String channel;
    @NotNull
    private String news;
    @NotNull
    private Date date;
    @NotNull
    private String logo;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 255022283)
    private transient NewsDao myDao;

    public News(String web, String channel, String news, Date date, String logo) {
        this.web = web;
        this.channel = channel;
        this.news = news;
        this.date = date;
        this.logo = logo;
    }

    @Generated(hash = 1096248143)
    public News(Long id, @NotNull String web, @NotNull String channel,
            @NotNull String news, @NotNull Date date, @NotNull String logo) {
        this.id = id;
        this.web = web;
        this.channel = channel;
        this.news = news;
        this.date = date;
        this.logo = logo;
    }

    @Generated(hash = 1579685679)
    public News() {
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 543991306)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNewsDao() : null;
    }
}


