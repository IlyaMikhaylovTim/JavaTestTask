package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public abstract class BaseService {

    protected RestTemplate restTemplate;

    public BaseService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    protected abstract void initService();

}
