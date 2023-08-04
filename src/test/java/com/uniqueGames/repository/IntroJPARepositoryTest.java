package com.uniqueGames.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.uniqueGames.model.Intro;
import java.util.List;
import java.util.Optional;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntroJPARepositoryTest {
    @Autowired
    private IntroJPARepository introJPARepository;

    @Test
    void testJpaSave() {
        // Create
        Intro intro = new Intro();
        intro.setCId("test");
        intro.setName("가보자가보자");
        intro.setContent("일단 박고 시작하는 가보자가보자입니다");
        this.introJPARepository.save(intro);
    }
    @Test
    void testJpaFindAll(){
        // Read
        List<Intro> all = this.introJPARepository.findAll();
        assertEquals(5, all.size());
        Intro q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getName());
    }

    @Test
    void testJpaFindById() {
        Optional<Intro> oq = this.introJPARepository.findById(1);
        if(oq.isPresent()) {
            Intro q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getName());
        }
    }

    @Test
    void testJpaFindByName() {
        Intro i = this.introJPARepository.findByName("베스트게이밍");
        assertEquals(5,i.getId());
    }

    @Test
    void testJpaFindByNameAndContent() {
        Intro i = this.introJPARepository.findByNameAndContent("베스트게이밍",
                "언제나 새로운 희망을 펼쳐나가는 베스트게이밍입니다");
        assertEquals(5,i.getId());
    }

    @Test
    void testJpaUpdate() {
        Optional<Intro> oq = this.introJPARepository.findById(1);
        assertTrue(oq.isPresent());
        Intro q = oq.get();
        q.setName("수정된 제목");
        this.introJPARepository.save(q);
    }

    @Test
    void testJpaDelete() {
        assertEquals(5, this.introJPARepository.count());
        Optional<Intro> oq = this.introJPARepository.findById(1);
        assertTrue(oq.isPresent());
        Intro q = oq.get();
        this.introJPARepository.delete(q);
        assertEquals(4, this.introJPARepository.count());
    }
}