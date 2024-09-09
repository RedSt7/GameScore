package com.sportradar.gamescore.gamescore.dao;

import com.sportradar.gamescore.gamescore.entities.Match;
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


}
