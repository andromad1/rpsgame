package com.example.nobeltesttask.rpsgame.model;

public class Round {
    private RoundResults res;
    private Choices compMove;
    private Choices playerMove;

    public RoundResults getRes() {
        return res;
    }

    public void setRes(RoundResults res) {
        this.res = res;
    }

    public Choices getCompMove() {
        return compMove;
    }

    public void setCompMove(Choices compMove) {
        this.compMove = compMove;
    }

    public Choices getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(Choices playerMove) {
        this.playerMove = playerMove;
    }

    @Override
    public String toString() {
        return "Round{" +
                "res=" + res +
                ", compMove=" + compMove +
                ", playerMove=" + playerMove +
                '}';
    }
}
