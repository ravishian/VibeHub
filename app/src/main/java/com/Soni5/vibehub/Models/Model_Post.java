package com.Soni5.vibehub.Models;

public class Model_Post  {

    String Id , Link , Time, Uid,Username , DP;

    public Model_Post(Model_Post obj) {
    }

    public Model_Post(String id, String link, String time, String uid, String username, String DP) {
        Id = id;
        Link = link;
        Time = time;
        Uid = uid;
        Username = username;
        this.DP = DP;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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
