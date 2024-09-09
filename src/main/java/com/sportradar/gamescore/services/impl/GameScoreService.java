package com.sportradar.gamescore.services.impl;

import com.sportradar.gamescore.dao.MatchDAO;
import com.sportradar.gamescore.entities.Match;
import com.sportradar.gamescore.exceptions.MatchAlreadyExistsException;
import com.sportradar.gamescore.exceptions.MatchDoesNotExistException;

import java.util.*;

public class GameScoreService {

    private static GameScoreService gameScoreService;
    private final MatchDAO matchDAO;

    private GameScoreService(){
        matchDAO = MatchDAO.getInstance();
    }

    //@Autowire I miss you. again
    public static GameScoreService getInstance(){
        if(gameScoreService == null){
            gameScoreService = new GameScoreService();
        }
        return gameScoreService;
    }


    public String startGame(String home, String away){
        Match match = new Match(home, away);

        if(matchDAO.findByID(match.getId()) != null)
            throw new MatchAlreadyExistsException();
        matchDAO.add(match);

        return match.getId();
    }

    public void updateScore(String id, int scoreHome, int scoreAway){
        if(matchDAO.findByID(id) == null)
            throw new MatchDoesNotExistException();

        matchDAO.update(id, scoreHome, scoreAway);
    }

    public void finishGame(String id){
        if(matchDAO.findByID(id) == null)
            throw new MatchDoesNotExistException();

        matchDAO.remove(id);
    }

    public List<Match> getSummary(){
        //copy arraylist in order avoid edit game matches without calling dao.update method
        return new ArrayList<>(matchDAO.findAllSorted());
    }

    public void clearAllMatches(){
        matchDAO.removeAll();
    }

}
