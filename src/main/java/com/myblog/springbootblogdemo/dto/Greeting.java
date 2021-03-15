package com.myblog.springbootblogdemo.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Greeting {

    private final long id;
    private final String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private Date date;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
