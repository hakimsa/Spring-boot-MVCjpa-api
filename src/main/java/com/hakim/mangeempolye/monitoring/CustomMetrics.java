package com.hakim.mangeempolye.monitoring;

import org.springframework.stereotype.Component;

import com.hakim.mangeempolye.services.EmployeeService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Component
public class CustomMetrics {
    
    private final Counter employeeCreatedCounter;
    private final Timer employeeResponseTime;

    public CustomMetrics(MeterRegistry meterRegistry, EmployeeService employeeService) {
        this.employeeCreatedCounter = Counter.builder("employees.created.count")
            .description("Total employees created")
            .register(meterRegistry);
            
        this.employeeResponseTime = Timer.builder("employees.response.time")
            .description("Employee API response time")
            .register(meterRegistry);
            
        Gauge.builder("employees.active.count", employeeService, EmployeeService::getActiveEmployeeCount)
            .description("Current active employees")
            .register(meterRegistry);
    }
    
    public void incrementEmployeeCreated() {
        employeeCreatedCounter.increment();
    }
    
    public Timer.Sample startTimer(MeterRegistry meterRegistry) {
        return Timer.start(meterRegistry);
    }
    
    public void stopTimer(Timer.Sample sample) {
        sample.stop(employeeResponseTime);
    }
}
