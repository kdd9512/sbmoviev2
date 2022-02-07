package com.sbmoviev2.repository;

import com.sbmoviev2.entity.Movie;
import com.sbmoviev2.entity.Poster;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
