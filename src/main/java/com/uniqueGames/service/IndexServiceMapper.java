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

    public List<Game> getGameListByCId(String cId){
        return indexMapper.getGameListByCId(cId);
    }

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

    public int hasLiked(String memberId, int gId) {
        return indexMapper.hasLiked(memberId, gId);
    }
    public int getGameLikeCount(int gId) {
        return indexMapper.getGameLikeCount(gId);
    }

    public void addLikeInfo(String memberId, int gId) {
        indexMapper.addLikeInfo(memberId, gId);
    }

    public void removeLikeInfo(String memberId, int gId) {
        indexMapper.removeLikeInfo(memberId, gId);
    }

}
