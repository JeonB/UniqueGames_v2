package com.uniqueGames.service;


import com.uniqueGames.model.Game;
import com.uniqueGames.repository.IndexMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceMapper {

    @Autowired
    IndexMapper indexMapper;

    public List<Game> getGameList() {
        return indexMapper.getGameList();
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

    public int hasLiked(int gid, int mid) {
        return indexMapper.hasLiked(gid, mid);
    }

    public void addLikeInfo(int gid, int mid) {
        indexMapper.addLikeInfo(gid, mid);
    }

    public void removeLikeInfo(int gid, int mid) {
        indexMapper.removeLikeInfo(gid, mid);
    }

}
