package com.sportradar.gamescore.gamescore.dao;

import com.sportradar.gamescore.gamescore.entities.Match;

import java.util.*;

public class MatchDAO {

    java.util.logging.Logger logger =  java.util.logging.Logger.getLogger(MatchDAO.class.getName());


    //hashmap for quick finding the match object by teams name.
    private final HashMap<String, Match> hashMap;
    //tree map for sorting. arrayList inside for multiple matches with same scores.
    private final TreeMap<Integer, ArrayList<Match>> scoreBoard;



    private static MatchDAO matchDAO;

    private MatchDAO(){
        hashMap = new HashMap<>();
        scoreBoard = new TreeMap<>(Collections.reverseOrder());
    }
    //@Autowire I miss you.
    public static MatchDAO getInstance(){
        if(matchDAO == null){
            matchDAO = new MatchDAO();
        }

        return matchDAO;
    }

    public void add(Match match){
        logger.info("adding match : "+match);
        hashMap.put(match.getId(), match);
        int score = match.getScoreA() + match.getScoreB();

        List<Match> scoreList = scoreBoard.computeIfAbsent(score, k -> new ArrayList<>());
        scoreList.add(match);
    }

    public void remove(String id){
        logger.info("removing match with id : "+id);
        hashMap.remove(id);
    }

    public List<Match> findAll(){
        return new ArrayList<>(hashMap.values());
    }

    public void removeAll(){
        hashMap.clear();
    }

    public List<Match> findAllSorted(){
        List<Match> sortedList = new ArrayList<>();
        for (List<Match> games : scoreBoard.values()) {
            sortedList.addAll(games);
        }
        return sortedList;
    }
}
