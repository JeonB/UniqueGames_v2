package com.uniqueGames.repository;


import com.uniqueGames.model.Game;
import java.util.List;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexMapper {

    @Select("SELECT ID, NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION FROM GAME")
    List<Game> getGameList();

    @Select("SELECT * FROM GAME WHERE ID=?#{id}")
    Game getGame(Game vo);

    @Select("SELECT * FROM GAME WHERE ID=#{id}")
    Game getGameForIndex(int id);

    @Select("SELECT * FROM GAME WHERE DONATION_STATUS = 1")
    List<Game> getDonationList();

    @Select("SELECT COUNT(*) FROM LIKES ORDER BY DESC")
    int getGameLikeCount();

    @Select("SELECT ID, NAME, IMAGE_PATH, GAME_GENRE, DONATION_STATUS, DESCRIPTION FROM GAME")
    List<Game> getRankingList();

    @Insert("INSERT INTO GAME(NAME,IMAGE_PATH,GAME_GENRE,DONATION_STATUS,DESCRIPTION) VALUES(#{name},#{imagePath},#{gameGenre},#{donationStatus},#{description})")
    void insertGame(Game vo);

    @Update("UPDATE GAME SET NAME=#{name} WHERE ID=#{id}")
    void updateGame(Game vo);

    @Delete("DELETE GAME WHERE ID=#{id}")
    void deleteGame(Game vo);

    @Select("SELECT COUNT(*) FROM LIKES where G_ID = #{gid} and MEMBER_ID = #{mid}")
    int hasLiked(String mid, String gid);

    @Update("INSERT INTO LIKES (G_ID, MEMBER_ID) VALUE(#{gid},#{mid})")
    void addLikeInfo(String mid, String gid);

    @Delete("DELETE FROM LIKES WHERE MEMBER_ID = #{mid} and G_ID = #{gid}")
    void removeLikeInfo(String mid, String gid);



}
