package com.sportradar.gamescore.entities;

import org.junit.jupiter.api.Test;

public class MatchTest {


    @Test
    void compareMatchesDifferentScores(){

        Match match = new Match("Olympiakos", "Panathinaikos");
        match.setScoreA(5);
        match.setScoreB(1);

        Match match2 = new Match("Olympiakos", "AEK");
        match2.setScoreA(3);
        match2.setScoreB(1);

        assert(match.compareTo(match2) > 0);
        assert(match2.compareTo(match) < 0);
    }

    @Test
    void compareMatchesSameScores(){

        Match match = new Match("Olympiakos", "Panathinaikos");
        match.setScoreA(5);
        match.setScoreB(1);

        try {
            //without this sleep, both matches had some starting dates
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Match match2 = new Match("Olympiakos", "AEK");
        match2.setScoreA(5);
        match2.setScoreB(1);

        assert(match.compareTo(match2) < 0);
        assert(match2.compareTo(match) > 0);
    }
}
