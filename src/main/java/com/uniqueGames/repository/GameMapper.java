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
    @Select("SELECT COUNT(*) FROM GAME")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM GAME WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

    @Select("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, id, name FROM GAME) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Game> aGetGameList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM GAME WHERE ID = #{id}")
    Game aGetGame(int id);
}
