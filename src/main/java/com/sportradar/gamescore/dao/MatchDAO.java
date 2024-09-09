package com.sportradar.gamescore.dao;

import com.sportradar.gamescore.entities.Match;

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
        Match match = hashMap.get(id);
        hashMap.remove(id);

        int score = match.getScoreA() + match.getScoreB();
        scoreBoard.get(score).remove(match);
    }


    public void update(String id, int scoreA, int scoreB){
        Match match = hashMap.get(id);

        int previousScore = match.getScoreA() + match.getScoreB();

        match.setScoreA(scoreA);
        match.setScoreB(scoreB);

        int newScore = match.getScoreA() + match.getScoreB();

        scoreBoard.get(previousScore).remove(match);

        List<Match> scoreList = scoreBoard.computeIfAbsent(newScore, k -> new ArrayList<>());
        scoreList.add(match);

    }

    public Match findByID(String id){
        return hashMap.get(id);
    }

    public List<Match> findAll(){
        return new ArrayList<>(hashMap.values());
    }

    public void removeAll(){
        hashMap.clear();
        scoreBoard.clear();
    }

    public List<Match> findAllSorted(){
        List<Match> sortedList = new ArrayList<>();
        for (List<Match> games : scoreBoard.values()) {
            sortedList.addAll(games);
        }
        return sortedList;
    }
}
