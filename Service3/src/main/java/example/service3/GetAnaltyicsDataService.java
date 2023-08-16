package example.service3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAnaltyicsDataService {
    private final AnalyticsDataRepository analyticsDataRepository;

    @Autowired
    public GetAnaltyicsDataService(AnalyticsDataRepository analyticsDataRepository) {
        this.analyticsDataRepository = analyticsDataRepository;
    }

    public AnalyticsData getLastRecord() {
        System.out.println();
        return analyticsDataRepository.findTopByOrderByIdDesc();
    }
}