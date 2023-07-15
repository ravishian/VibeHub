package com.Soni5.vibehub.Models;

public class Model_Comment {


    String Username,Comment;

    public Model_Comment() {
    }

    public Model_Comment(String username, String comment) {
        Username = username;
        Comment = comment;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
