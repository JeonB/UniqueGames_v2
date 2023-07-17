package com.uniqueGames.repository;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.Game;
import java.util.List;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@Mapper
public interface IndexMapper {

    @Select("SELECT\n" +
            "    GAME.ID,\n" +
            "    GAME.NAME,\n" +
            "    GAME.IMAGE_PATH,\n" +
            "    GAME.GAME_GENRE,\n" +
            "    GAME.DONATION_STATUS,\n" +
            "    GAME.DESCRIPTION,\n" +
            "    COUNT(LIKES.ID) AS LIKE_COUNT\n" +
            "FROM\n" +
            "    GAME\n" +
            "        LEFT JOIN\n" +
            "    LIKES ON GAME.ID = LIKES.G_ID\n" +
            "GROUP BY\n" +
            "    GAME.ID,\n" +
            "    GAME.NAME,\n" +
            "    GAME.IMAGE_PATH,\n" +
            "    GAME.GAME_GENRE,\n" +
            "    GAME.DONATION_STATUS,\n" +
            "    GAME.DESCRIPTION;")
    List<Game> getGameList();

    @Select("SELECT * FROM GAME WHERE ID=?#{id}")
    Game getGame(Game vo);

    @Select("SELECT * FROM GAME WHERE ID=#{id}")
    Game getGameForIndex(int id);

    @Select("SELECT * FROM GAME WHERE DONATION_STATUS = 1")
    List<Game> getDonationList();

    @Select("SELECT COUNT(*) FROM LIKES WHERE G_ID= #{gameId}")
    int getGameLikeCount(@Param("gameId") String gameId);

    @Select("SELECT ID, NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION FROM GAME")
    List<Game> getRankingList();

    @Insert("INSERT INTO GAME(NAME,IMAGE_PATH,GAME_GENRE,DONATION_STATUS,DESCRIPTION) VALUES(#{name},#{imagePath},#{gameGenre},#{donationStatus},#{description})")
    void insertGame(Game vo);

    @Update("UPDATE GAME SET NAME=#{name} WHERE ID=#{id}")
    void updateGame(Game vo);

    @Delete("DELETE GAME WHERE ID=#{id}")
    void deleteGame(Game vo);

    @Select("SELECT COUNT(*) FROM LIKES where G_ID = #{gameId} and MEMBER_ID = #{mid}")
    int hasLiked(String mid, String gameId);

    @Update("INSERT INTO LIKES (G_ID, MEMBER_ID) VALUE(#{gameId},#{mid})")
    void addLikeInfo(String mid, String gameId);

    @Delete("DELETE FROM LIKES WHERE MEMBER_ID = #{mid} and G_ID = #{gameId}")
    void removeLikeInfo(String mid, String gameId);



}
