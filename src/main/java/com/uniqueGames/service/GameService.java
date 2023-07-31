package com.uniqueGames.service;

import com.uniqueGames.model.Game;
import com.uniqueGames.repository.GameMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Repository
@Service
public class GameService {
    @Autowired
    GameMapper gameMapper;

    public int totRowCount() {
        return gameMapper.totRowCount();
    }

    public ArrayList<Game> aGetGameList(String order1, String order2, int start, int end) {
        ArrayList<Game> gList = new ArrayList<>();
        for (Game game : gameMapper.aGetGameList(order1, order2, start, end)) {
            gList.add(game);
        }
        return gList;
    }

    public Game aGetGame(int id) {
        return gameMapper.getGameForIndex(id);
    }


    public Game getGameForIndex(int id) {
        return gameMapper.getGameForIndex(id);
    }

    public List<Game> getGameImg(int gId) {
        return gameMapper.getGameImg(gId);
    }

    public int aRegisterGame(String name, String genre, String description) {
        return gameMapper.aRegisterGame(name, genre, description);
    }

    public int aGetGid(String name) {
        return gameMapper.aGetGid(name);
    }

    public int aDeleteGame(int gid) {
        return gameMapper.aDeleteGame(gid);
    }

    public int aUpdateGame(String name, String genre, String description, int gid) {
        return gameMapper.aUpdateGame(name, genre, description, gid);
    }

    public int aUpdateGameImg(String imagePath) {
        return gameMapper.aUpdateGameImg(imagePath);
    }

    public String aGetGameImg(int id) {
        return gameMapper.aGetGameImg(id);
    }

    public int aRegisterGameImg(int gid, String imagePath) {
        return gameMapper.aRegisterGameImg(gid, imagePath);
    }
}
