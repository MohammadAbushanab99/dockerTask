package example.service3;

import example.service3.AnalyticsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnalyticsDataService {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private AnalyticsDataRepository analyticsDataRepository;

    public AnalyticsDataService(JdbcTemplate jdbcTemplate, AnalyticsDataRepository analyticsDataRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.analyticsDataRepository = analyticsDataRepository;
    }

    public void saveAnalyticsData() {
        int min = 0;
        int max = 0;
        double average = 0;
        List<Integer> numbers =getAnalyticsData();
        System.out.println(!numbers.isEmpty());
        if (!numbers.isEmpty()) {

             min = Collections.min(numbers);
             max = Collections.max(numbers);
             average = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);


        }



        AnalyticsData analyticsData = new AnalyticsData();
        analyticsData.setAverage(average);
        analyticsData.setMin((double) min);
        analyticsData.setMax((double) max);
        analyticsDataRepository.save(analyticsData);

        System.out.println("getAllAnalyticsData().isEmpty()");
        List<AnalyticsData> a =getAllAnalyticsData();
        for (AnalyticsData analyticsData1 : a){

            System.out.println(analyticsData1.getAverage());
            System.out.println(analyticsData1.getMax());
            System.out.println(analyticsData1.getMin());
            System.out.println("===============================");

        }
    }

    public List<AnalyticsData> getAllAnalyticsData() {
        return analyticsDataRepository.findAll();
    }

    public List<Integer> getAnalyticsData() {
        List<Integer> numbers = new ArrayList<>();
        try {
            String selectQuery = "SELECT value FROM number_entry";
            numbers = jdbcTemplate.queryForList(selectQuery, Integer.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers;
    }


}