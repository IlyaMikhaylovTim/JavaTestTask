package com.trial.popularitycalcwithtests.popularitycalc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerStartsTest {

    @Autowired
    private Controller controller;

    @Test
    public void controllerStarts() throws Exception {
        assertThat(controller).isNotNull();
    }
}
