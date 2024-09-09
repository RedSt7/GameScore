package com.sportradar.gamescore.gamescore.entities;

public class Match {

    private String id;
    private String teamA;

    private String teamB;

    private int scoreA;

    private int scoreB;


    public Match(String teamA, String teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.id = teamA + "-"+ teamB;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Match() {
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    @Override
    public String toString() {
        return "Match{" +
                "teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                '}';
    }
}
