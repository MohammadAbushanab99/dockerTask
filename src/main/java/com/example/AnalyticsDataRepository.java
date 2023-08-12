package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnalyticsDataRepository extends MongoRepository<AnalyticsData, String> {
    List<AnalyticsData> findByAverageGreaterThan(double threshold);
}
