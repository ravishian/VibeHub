package com.Soni5.vibehub.Models;

public class Model_Search
{

    public Model_Search() {
    }

    String Username , Name , Id;

    public Model_Search(String username, String name , String id) {
        Username = username;
        Name = name;
        Id = id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
