package com.aktway.services.core.healthcheck;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthCheck implements HealthIndicator {

    public ServiceHealthCheck() {
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}