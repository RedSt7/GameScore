package com.sportradar.gamescore.gamescore.dao;

import com.sportradar.gamescore.gamescore.entities.Match;

import java.util.ArrayList;
import java.util.HashMap;

public class MatchDAO {


    private final HashMap<String, Match> hashMap;


    private static MatchDAO matchDAO;

    private MatchDAO(){
        hashMap = new HashMap<>();
    }
    //@Autowire I miss you.
    public static MatchDAO getInstance(){
        if(matchDAO == null){
            matchDAO = new MatchDAO();
        }

        return matchDAO;
    }

    public void add(Match match){
        hashMap.put(match.getId(), match);
    }

    public void remove(String id){
        hashMap.remove(id);
    }

    public ArrayList<Match> findAll(){
        return new ArrayList<>(hashMap.values());
    }

    public void removeAll(){
        hashMap.clear();
    }
}
