package com.sportradar.gamescore.entities;

import java.time.LocalDateTime;

public class Match implements Comparable<Match>{

    private String id;
    private String teamA;

    private String teamB;

    private int scoreA;

    private int scoreB;

    private LocalDateTime startedDate;


    public Match(String teamA, String teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.id = teamA + "-"+ teamB;
        startedDate = LocalDateTime.now();
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

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", teamA='" + teamA + '\'' +
                ", teamB='" + teamB + '\'' +
                ", scoreA=" + scoreA +
                ", scoreB=" + scoreB +
                ", startedDate=" + startedDate +
                '}';
    }


    @Override
    public int compareTo(Match o) {
        //larger score means match with higher priority
        //if score is equal, most recently started match with higher priority

        int compareScore = Integer.compare((this.scoreA+this.scoreB), (o.scoreA+o.scoreB));

        int compareDateStarted = this.startedDate.compareTo(o.getStartedDate());

        return (compareScore == 0) ? compareDateStarted : compareScore;
    }
}
