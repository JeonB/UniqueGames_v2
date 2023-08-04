package com.uniqueGames.repository;

import com.uniqueGames.model.Intro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroJPARepository extends JpaRepository<Intro, Integer> {

    Intro findByName(String name);

    Intro findByNameAndContent(String name,String content);
}
