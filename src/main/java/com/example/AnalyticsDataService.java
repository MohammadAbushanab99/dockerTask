package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsDataService {
    @Autowired
    private AnalyticsDataRepository analyticsDataRepository;

    public void saveAnalyticsData(Double average, Double min, Double max) {
        AnalyticsData analyticsData = new AnalyticsData();
        analyticsData.setAverage(average);
        analyticsData.setMin(min);
        analyticsData.setMax(max);
        analyticsDataRepository.save(analyticsData);
    }

    public List<AnalyticsData> getAllAnalyticsData() {
        return analyticsDataRepository.findAll();
    }


}