package com.sportradar.gamescore.dao;

import com.sportradar.gamescore.entities.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchDAOTest {



    MatchDAO matchDao;


    @BeforeEach
    void setUp(){
        matchDao = MatchDAO.getInstance();
        matchDao.removeAll();
    }


    @Test
    void addMultipleMatches(){
        Match match = new Match("Olympiakos", "Panathinaikos");
        matchDao.add(match);

        assertEquals(1, matchDao.findAll().size());

        Match match2 = new Match("Olympiakos", "AEK");
        matchDao.add(match2);

        assertEquals(2, matchDao.findAll().size());
    }

    @Test
    void removeMatch(){
        Match match = new Match("Olympiakos", "Panathinaikos");
        matchDao.add(match);
        Match match2 = new Match("Olympiakos", "AEK");
        matchDao.add(match2);

        assertEquals(2, matchDao.findAll().size());

        matchDao.remove(match.getId());

        assertEquals(1, matchDao.findAll().size());
        assertEquals(match2, matchDao.findAll().get(0));
    }

    @Test
    void removeAllMatches(){
        Match match = new Match("Olympiakos", "Panathinaikos");
        matchDao.add(match);
        Match match2 = new Match("Olympiakos", "AEK");
        matchDao.add(match2);
        assertEquals(2, matchDao.findAll().size());
        matchDao.removeAll();
        assertEquals(0, matchDao.findAll().size());
    }

    @Test
    void update(){
        Match match = new Match("Olympiakos", "Panathinaikos");
        matchDao.add(match);
        matchDao.update(match.getId(), 5,4);

        assertEquals(5, match.getScoreA());
        assertEquals(4, match.getScoreB());
        assertEquals(match, matchDao.findAllSorted().get(0));
    }

    @Test
    void findAll(){
        Match match = new Match("Olympiakos", "Panathinaikos");
        matchDao.add(match);

        assertEquals(1, matchDao.findAll().size());

        Match match2 = new Match("Olympiakos", "AEK");
        matchDao.add(match2);

        assertEquals(2, matchDao.findAll().size());
    }

    @Test
    void findSorted(){
        //total score 3
        Match match1 = new Match("Olympiakos", "PAOK");
        match1.setScoreA(2);
        match1.setScoreB(1);
        matchDao.add(match1);
        //total score 5
        Match match2 = new Match("Olympiakos", "Panathinaikos");
        match2.setScoreA(5);
        match2.setScoreB(0);
        matchDao.add(match2);
        //total score 4
        Match match3 = new Match("Olympiakos", "AEK");
        match3.setScoreA(3);
        match3.setScoreB(1);
        matchDao.add(match3);

        assertEquals(match2, matchDao.findAllSorted().get(0));
        assertEquals(match3, matchDao.findAllSorted().get(1));
        assertEquals(match1, matchDao.findAllSorted().get(2));

    }


}
