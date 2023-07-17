package com.uniqueGames.service;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Member;
import com.uniqueGames.repository.IndexMapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class IndexServiceMapper {

    @Autowired
    IndexMapper indexMapper;

    public List<Game> getGameList() { return indexMapper.getGameList(); }

    public Game getGame(Game vo) {
        return indexMapper.getGame(vo);
    }

    public Game getGameForIndex(int id) {
        return indexMapper.getGameForIndex(id);
    }

    public List<Game> getDonationList() {
        return indexMapper.getDonationList();
    }

    public List<Game> getRankingList() {
        return indexMapper.getRankingList();
    }

    public void insertGame(Game vo) {
        indexMapper.insertGame(vo);
    }

    public void updateGame(Game vo) {
        indexMapper.updateGame(vo);
    }

    public void deleteGame(Game vo) {
        indexMapper.deleteGame(vo);
    }

    public int hasLiked(String mid, String gameId) {
        return indexMapper.hasLiked(mid, gameId);
    }
    public int getGameLikeCount(String gameId) {
        return indexMapper.getGameLikeCount(gameId);
    }

    public void addLikeInfo(String mid, String gameId) {
        indexMapper.addLikeInfo(mid, gameId);
    }

    public void removeLikeInfo(String mid, String gameId) {
        indexMapper.removeLikeInfo(mid, gameId);
    }

}
