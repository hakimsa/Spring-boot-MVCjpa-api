package com.hakim.mangeempolye.monitoring;

import org.springframework.stereotype.Component;

import com.hakim.mangeempolye.services.EmployeeService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class CustomMetrics {

    private final MeterRegistry meterRegistry; // 👈 AQUÍ

    public CustomMetrics(MeterRegistry meterRegistry, EmployeeService employeeService) {
        this.meterRegistry = meterRegistry; // 👈 GUARDARLO

        Timer.builder("employees_response_time_seconds")
                .description("Employee API response time")
                .publishPercentiles(0.5, 0.95, 0.99)
                .publishPercentileHistogram()
                .register(meterRegistry);

        Gauge.builder("employees_active", employeeService, EmployeeService::getActiveEmployeeCount)
                .description("Current active employees")
                .register(meterRegistry);
    }

    
    public void incrementEmployeeCreated(String status) {
        Counter.builder("employees_created_total")
                .tag("status", status)
                .register(meterRegistry)
                .increment();
    }

    public Timer.Sample startTimer() {
        return Timer.start(meterRegistry);
    }

    public void stopTimer(Timer.Sample sample, String endpoint, String status) {
        sample.stop(
            Timer.builder("employees_response_time_seconds")
                    .tag("endpoint", endpoint)
                    .tag("status", status)
                    .register(meterRegistry)
        );
    }
}