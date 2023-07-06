package com.uniqueGames.repository;


import com.uniqueGames.model.Game;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexMapper {

    @Select("SELECT G.ID, G.NAME, G.IMAGE_PATH, G.GAME_GENRE, G.DONATION_STATUS, G.DESCRIPTION, L.LIKE_COUNT FROM GAME G INNER JOIN (SELECT ID, LENGTH(LIKELIST) - LENGTH(REPLACE(LIKELIST, ',', '')) AS LIKE_COUNT FROM GAME) L ON G.ID = L.ID")
    List<Game> getGameList();

    @Select("SELECT * FROM GAME WHERE ID=?#{id}")
    Game getGame(Game vo);

    @Select("SELECT * FROM GAME WHERE ID=#{id}")
    Game getGameForIndex(int id);

    @Select("SELECT * FROM GAME WHERE DONATION_STATUS = 1")
    List<Game> getDonationList();

    @Select("SELECT G.ID, G.NAME, G.IMAGE_PATH, G.GAME_GENRE, G.DONATION_STATUS, G.DESCRIPTION, L.LIKE_COUNT FROM GAME G INNER JOIN (SELECT ID, LENGTH(LIKELIST) - LENGTH(REPLACE(LIKELIST, ',', '')) + 1 AS LIKE_COUNT FROM GAME) L ON G.ID = L.ID ORDER BY LIKE_COUNT DESC")
    List<Game> getRankingList();

    @Insert("INSERT INTO GAME(NAME,IMAGE_PATH,GAME_GENRE,DONATION_STATUS,DESCRIPTION,LIKELIST) VALUES(#{name},#{imagePath},#{gameGenre},#{donationStatus},#{description},0)")
    void insertGame(Game vo);

    @Update("UPDATE GAME SET NAME=#{name} WHERE ID=#{id}")
    void updateGame(Game vo);

    @Delete("DELETE GAME WHERE ID=#{id}")
    void deleteGame(Game vo);

    @Select("SELECT COUNT(*) FROM GAME WHERE CONCAT(',', LIKELIST, ',') LIKE '%,#{gid},%' AND ID = #{mid}")
    int hasLiked(int gid, int mid);

    @Update("UPDATE GAME SET LIKELIST = CONCAT(IFNULL(LIKELIST, ''), ',#{mid}') WHERE ID = #{gid}")
    void addLikeInfo(int gid, int mid);

    @Delete("SET @gameId = #{gid}" +
            "SET @targetMid = #{mid}" +
            "UPDATE GAME SET LIKELIST = (CASE WHEN FIND_IN_SET(@targetMid, LIKELIST) > 0 THEN TRIM(BOTH ',' FROM REPLACE(CONCAT(',', LIKELIST, ','), CONCAT(',', @targetMid, ','), ','))" +
            "WHEN FIND_IN_SET(@targetMid, LIKELIST) = 1 THEN TRIM(BOTH ',' FROM REPLACE(CONCAT(@targetMid, ',', LIKELIST), CONCAT(@targetMid, ','), ''))" +
            "WHEN FIND_IN_SET(@targetMid, LIKELIST) = LENGTH(LIKELIST) - LENGTH(REPLACE(LIKELIST, ',', '')) + 1 THEN TRIM(BOTH ',' FROM REPLACE(CONCAT(LIKELIST, ',', @targetMid), CONCAT(',', @targetMid), ''))" +
            "ELSE LIKELIST END)WHERE ID = @gameId")
    void removeLikeInfo(int gid, int mid);



}
