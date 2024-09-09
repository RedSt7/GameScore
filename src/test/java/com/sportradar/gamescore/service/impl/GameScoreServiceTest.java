package com.sportradar.gamescore.service.impl;

import com.sportradar.gamescore.entities.Match;
import com.sportradar.gamescore.exceptions.MatchAlreadyExistsException;
import com.sportradar.gamescore.exceptions.MatchDoesNotExistException;
import com.sportradar.gamescore.services.impl.GameScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameScoreServiceTest {


    GameScoreService gameScoreService;


    @BeforeEach
    void setUp(){
        gameScoreService = GameScoreService.getInstance();
        gameScoreService.clearAllMatches();
    }



    @Test
    void startMatches(){
        gameScoreService.startGame("Olympiakos", "Panathinaikos");
        assertEquals(1, gameScoreService.getSummary().size());

        gameScoreService.startGame("Olympiakos", "AEK");
        assertEquals(2, gameScoreService.getSummary().size());
    }

    @Test
    void startSameMatch(){
        gameScoreService.startGame("Olympiakos", "Panathinaikos");
        assertThrows(MatchAlreadyExistsException.class, () ->
                gameScoreService.startGame("Olympiakos", "Panathinaikos"));

    }

    @Test
    void finishMatch(){
        String match1 = gameScoreService.startGame("Olympiakos", "Panathinaikos");
        String match2 = gameScoreService.startGame("Olympiakos", "AEK");

        assertEquals(2, gameScoreService.getSummary().size());

        gameScoreService.finishGame(match1);

        assertEquals(1, gameScoreService.getSummary().size());
        assertEquals(match2, gameScoreService.getSummary().get(0).getId());
    }
    @Test
    void finishMatchDoesNotExist(){
        assertThrows(MatchDoesNotExistException.class, () ->
                gameScoreService.finishGame("Olympiakos-Panathinaikos"));

    }

    @Test
    void updateMatch(){
        String match1 = gameScoreService.startGame("Olympiakos", "Paok");
        gameScoreService.updateScore(match1, 2, 1);

        assertEquals(match1, gameScoreService.getSummary().get(0).getId());
    }

    @Test
    void updateMatchDoesNotExist(){
        assertThrows(MatchDoesNotExistException.class, () ->
                gameScoreService.updateScore("Olympiakos-Panathinaikos", 1, 0));
    }



    @Test
    void findSorted(){
        String match1 = gameScoreService.startGame("Olympiakos", "Paok");
        gameScoreService.updateScore(match1, 2, 1);

        String match2 = gameScoreService.startGame("Olympiakos", "Panathinaikos");
        gameScoreService.updateScore(match2, 5, 0);

        String match3 = gameScoreService.startGame("Olympiakos", "AEK");
        gameScoreService.updateScore(match3, 3, 1);


        assertEquals(match2, gameScoreService.getSummary().get(0).getId());
        assertEquals(match3, gameScoreService.getSummary().get(1).getId());
        assertEquals(match1, gameScoreService.getSummary().get(2).getId());

    }


    @Test
    void findSortedWithSameScore() throws InterruptedException {
        String match1 = gameScoreService.startGame("Olympiakos", "Paok");
        Thread.sleep(100);
        String match2 = gameScoreService.startGame("Olympiakos", "Panathinaikos");

        gameScoreService.updateScore(match1, 2, 1);
        gameScoreService.updateScore(match2, 2, 1);


        assertEquals(match1, gameScoreService.getSummary().get(1).getId());
        assertEquals(match2, gameScoreService.getSummary().get(0).getId());
    }



    @Test
    void findSortedWithMultipleCases(){

        String match1 = gameScoreService.startGame("Mexico", "Canada");
        String match2 = gameScoreService.startGame("Spain", "Brazil");
        String match3 = gameScoreService.startGame("Germany", "France");
        String match4 = gameScoreService.startGame("Uruguay", "Italy");
        String match5 = gameScoreService.startGame("Argentina", "Australia");

        gameScoreService.updateScore(match1, 0, 5);
        gameScoreService.updateScore(match2, 10, 2);
        gameScoreService.updateScore(match3, 2, 2);
        gameScoreService.updateScore(match4, 6, 6);
        gameScoreService.updateScore(match5, 3, 1);

        List<Match> matchList = gameScoreService.getSummary();

        System.out.println(gameScoreService.getSummary());
        assertAll(
                () -> assertEquals(match4, matchList.get(0).getId()),
                () -> assertEquals(match2, matchList.get(1).getId()),
                () -> assertEquals(match1, matchList.get(2).getId()),
                () -> assertEquals(match5, matchList.get(3).getId()),
                () -> assertEquals(match3, matchList.get(4).getId())
                );
    }


}
