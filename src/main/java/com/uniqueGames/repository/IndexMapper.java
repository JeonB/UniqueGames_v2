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
            "    TB_GAME.ID,\n" +
            "    TB_GAME.NAME,\n" +
            "    TB_GAME.IMAGE_PATH,\n" +
            "    TB_GAME.GAME_GENRE,\n" +
            "    TB_GAME.DONATION_STATUS,\n" +
            "    TB_GAME.DESCRIPTION,\n" +
            "    COUNT(TB_LIKE.ID) AS LIKE_COUNT\n" +
            "FROM\n" +
            "    TB_GAME\n" +
            "        LEFT JOIN\n" +
            "    TB_LIKE ON TB_GAME.ID = TB_LIKE.G_ID\n" +
            "GROUP BY\n" +
            "    TB_GAME.ID,\n" +
            "    TB_GAME.NAME,\n" +
            "    TB_GAME.IMAGE_PATH,\n" +
            "    TB_GAME.GAME_GENRE,\n" +
            "    TB_GAME.DONATION_STATUS,\n" +
            "    TB_GAME.DESCRIPTION;")
    List<Game> getGameList();

    @Select("SELECT * FROM TB_GAME WHERE ID=?#{id}")
    Game getGame(Game vo);

    @Select("SELECT * FROM TB_GAME WHERE ID=#{id}")
    Game getGameForIndex(int id);

    @Select("SELECT * FROM TB_GAME WHERE DONATION_STATUS = 1")
    List<Game> getDonationList();

    @Select("SELECT COUNT(*) FROM TB_LIKE WHERE G_ID= #{gId}")
    int getGameLikeCount(@Param("gId") int gId);

    @Select("SELECT ID, NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION FROM TB_GAME")
    List<Game> getRankingList();

    @Insert("INSERT INTO TB_GAME(NAME,IMAGE_PATH,GAME_GENRE,DONATION_STATUS,DESCRIPTION) VALUES(#{name},#{imagePath},#{gameGenre},#{donationStatus},#{description})")
    void insertGame(Game vo);

    @Update("UPDATE TB_GAME SET NAME=#{name} WHERE ID=#{id}")
    void updateGame(Game vo);

    @Delete("DELETE TB_GAME WHERE ID=#{id}")
    void deleteGame(Game vo);

    @Select("SELECT COUNT(*) FROM TB_LIKE where G_ID = #{gId} and MEMBER_ID = #{memberId}")
    int hasLiked(@Param("memberId") String memberId, @Param("gId") int gId);

    @Update("INSERT INTO TB_LIKE (G_ID, MEMBER_ID) VALUE(#{gId},#{memberId})")
    void addLikeInfo(@Param("memberId") String memberId, @Param("gId") int gId);

    @Delete("DELETE FROM TB_LIKE WHERE MEMBER_ID = #{memberId} and G_ID = #{gId}")
    void removeLikeInfo(@Param("memberId") String memberId, @Param("gId") int gId);



}
