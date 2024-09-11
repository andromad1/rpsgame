package com.example.nobeltesttask.rpsgame.model;

public class Game {
    private int compWins = 0;
    private int userWins = 0;
    private GameStatuses status = GameStatuses.NOT_STARTED;

    public int getCompWins() {
        return compWins;
    }

    public void setCompWins(int compWins) {
        this.compWins = compWins;
    }

    public void incCompWins() {
        compWins++;
    }

    public int getUserWins() {
        return userWins;
    }

    public void setUserWins(int userWins) {
        this.userWins = userWins;
    }

    public void incUserWins() {
        userWins++;
    }

    public GameStatuses getStatus() {
        return status;
    }

    public void setStatus(GameStatuses status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Game{" +
                "compWins=" + compWins +
                ", userWins=" + userWins +
                ", status=" + status +
                '}';
    }
}
