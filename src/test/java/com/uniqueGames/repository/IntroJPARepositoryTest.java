package com.uniqueGames.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.uniqueGames.model.Intro;
import java.time.LocalDateTime;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntroJPARepositoryTest {
    @Autowired
    private IntroJPARepository introJPARepository;

    @Test
    void testJpa() {
        Intro intro = new Intro();
        intro.setName("sbb가 무엇인가요?");
        intro.setContent("sbb에 대해서 알고 싶습니다.");
        this.introJPARepository.save(intro);  // 첫번째 질문 저장

        Intro intro2 = new Intro();
        intro2.setName("스프링부트 모델 질문입니다.");
        intro2.setContent("id는 자동으로 생성되나요?");
        this.introJPARepository.save(intro2);  // 두번째 질문 저장
    }
}