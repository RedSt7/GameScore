package com.sportradar.gamescore.service.impl;

import com.sportradar.gamescore.exceptions.MatchAlreadyExistsException;
import com.sportradar.gamescore.exceptions.MatchDoesNotExistException;
import com.sportradar.gamescore.services.impl.GameScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
