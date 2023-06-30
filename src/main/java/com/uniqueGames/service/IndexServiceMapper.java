package com.uniqueGames.service;


import com.uniqueGames.model.GameVo;
import com.uniqueGames.repository.IndexMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceMapper {

    @Autowired
    IndexMapper indexMapper;

    public List<GameVo> getGameList() {
        return indexMapper.getGameList();
    }

    public GameVo getGame(GameVo vo) {
        return indexMapper.getGame(vo);
    }
    public GameVo getGameForIndex(int id) {
        return indexMapper.getGameForIndex(id);
    }

    public List<GameVo> getDonationList() {
        return indexMapper.getDonationList();
    }

    public List<GameVo> getRankingList() {
        return indexMapper.getRankingList();
    }

    public void insertGame(GameVo vo) {
        indexMapper.insertGame(vo);
    }

    public void updateGame(GameVo vo) {
        indexMapper.updateGame(vo);
    }

    public void deleteGame(GameVo vo) {
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
