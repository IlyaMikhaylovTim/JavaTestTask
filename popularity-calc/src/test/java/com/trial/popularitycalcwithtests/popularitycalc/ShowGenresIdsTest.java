package com.trial.popularitycalcwithtests.popularitycalc;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ShowGenresIdsTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldContainListOfGenres() throws Exception {
        String content = "Genres ids: [10752, 10402, 99, 35, 36, 37, 12, 878, 14, 9648, 80, 16, 10770, 18, 53, 27, 28, 10749, 10751]";
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/showGenresIds",
                String.class)).contains(content);
    }
}