package com.trial.popularitycalcwithtests.popularitycalc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowGenrePopularityTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void validGenreIdRequest() throws Exception {
        String content = "Still in progress, come back later...";
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/showGenrePopularity/35",
                String.class)).contains(content);
    }

    @Test
    public void invalidGenreIdRequest() throws Exception {
        String content = "No such genre id";
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/showGenrePopularity/34",
                String.class)).contains(content);
    }

    @Test
    public void shouldContainTemplateResultMessage() throws Exception {
        while(restTemplate.getForObject("http://localhost:" + port + "/showGenrePopularity/35",
                String.class).contains("Still in progress, come back later...")) {}

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/showGenrePopularity/35",
                String.class)).contains("Mean popularity");
    }

}