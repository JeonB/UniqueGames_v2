package com.uniqueGames.repository;

import com.uniqueGames.model.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GameMapper {
    @Select("SELECT COUNT(*) FROM TB_GAME")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM TB_GAME WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

    @Select("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, id, name FROM TB_GAME) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Game> aGetGameList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM TB_GAME WHERE ID=#{id}")
    Game getGameForIndex(int id);

    @Select("SELECT * FROM TB_GAME_IMAGE WHERE G_ID=#{gId}")
    List<Game> getGameImg(int gId);

    @Select("SELECT * FROM TB_GAME_IMAGE WHERE G_ID=#{gId} LIMIT 1")
    Game getOneFile(int gId);

    @Select("SELECT * FROM TB_GAME")
    List<Game> getGameAllList();
}
