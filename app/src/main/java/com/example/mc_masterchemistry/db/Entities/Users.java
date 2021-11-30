package com.example.mc_masterchemistry.db.Entities;



public class Users {
    private String nick;
    private int gemas;

    public Users() {
    }

    public Users(String nick, int gemas) {
        this.nick = nick;
        this.gemas = gemas;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getGemas() {
        return gemas;
    }

    public void setGemas(int gemas) {
        this.gemas = gemas;
    }
}
