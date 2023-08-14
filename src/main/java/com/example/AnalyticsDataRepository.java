package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AnalyticsDataRepository extends MongoRepository<AnalyticsData, String> {
    AnalyticsData findTopByOrderByIdDesc();
}
