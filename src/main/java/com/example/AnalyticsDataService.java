package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsDataService {

    private AnalyticsDataRepository analyticsDataRepository;

    public AnalyticsDataService(AnalyticsDataRepository analyticsDataRepository) {
        this.analyticsDataRepository = analyticsDataRepository;
    }

    public void saveAnalyticsData(Double average, Double min, Double max) {
        AnalyticsData analyticsData = new AnalyticsData();
        analyticsData.setAverage(average);
        analyticsData.setMin(min);
        analyticsData.setMax(max);
        System.out.println("analyticsData.getMax()");
        System.out.println(analyticsData.getMax());
        analyticsDataRepository.save(analyticsData);
    }

    public List<AnalyticsData> getAllAnalyticsData() {
        return analyticsDataRepository.findAll();
    }


}