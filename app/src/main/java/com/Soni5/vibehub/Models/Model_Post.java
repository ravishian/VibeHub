package com.Soni5.vibehub.Models;

public class Model_Post  {

    String Id , Link , Time, Uid;

    public Model_Post() {
    }

    public Model_Post(String id, String link, String time, String uid) {
        Id = id;
        Link = link;
        Time = time;
        Uid = uid;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
