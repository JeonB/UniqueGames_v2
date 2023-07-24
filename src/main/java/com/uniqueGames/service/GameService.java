package com.uniqueGames.service;

import com.uniqueGames.model.Game;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.Order;
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

    public int totRowCountSearch(String keyword) {
        return gameMapper.totRowCountSearch(keyword);
    }

    public ArrayList<Game> aGetGameList(String order1, String order2, int start, int end) {
        ArrayList<Game> gList = new ArrayList<>();
        for (Game game : gameMapper.aGetGameList(order1, order2, start, end)) {
            gList.add(game);
        }
        return gList;
    }

    public Game aGetGame(int id) {
        return gameMapper.aGetGame(id);
    }


    /**
     * @param cartList 추가된 게임을 담을 카트 리스트
     * @return 장바구니 리스트
     */
    public ArrayList<Order> addGameInfo(ArrayList<Order> cartList) {
        for (int i = 0; i < cartList.size(); i++) {
            int gid = cartList.get(i).getGId();
            Game game = gameMapper.aGetGame(gid);

            cartList.get(i).setGameImg("../images/"+game.getUploadImg());
            cartList.get(i).setGametitle(game.getName());
        }

        return cartList;
    }

    public Game getGameForIndex(int id) {
        return gameMapper.getGameForIndex(id);
    }

    public List<Game> getGameImg(int gId) {
        return gameMapper.getGameImg(gId);
    }
}
