package com.sbmoviev2.repository;

import com.sbmoviev2.entity.Movie;
import com.sbmoviev2.entity.Poster;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository repository;

    @Test
    public void testDummyInsert(){
        log.info("testDummyInsert==========================================================");

        Movie movie = Movie.builder().title("test1").build();

        movie.addPoster(Poster.builder().fname("file1.jpg").build());
        movie.addPoster(Poster.builder().fname("file2.jpg").build());

        repository.save(movie);

        log.info(movie.getMno());

    }

    @Test
    @Transactional // LazyInitialization 에러 방지를 위한 annotation. 지연조회 시점까지 세션을 유지시킨다.
    @Commit
    public void testAddPoster(){

        // getOne 은 deprecated 되었음. 때문에 지연조회까지 세션유지를 위한 @Transactional annotation 이 추가로 필요하다.
        Movie movie = repository.getById(1L); // DB 상 존재하는 영화번호.

        movie.addPoster(Poster.builder().fname("file3.jpg").build()); // 새 poster 객체.

        repository.save(movie);

    }

    @Test
    @Transactional
    @Commit
    public void testRemovePoster(){

        Movie movie = repository.getById(1L);

        movie.removePoster(2L);

        repository.save(movie);
    }

}
