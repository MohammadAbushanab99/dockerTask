package example.service3;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalyticsDataRepository extends MongoRepository<AnalyticsData, String> {
    AnalyticsData findTopByOrderByIdDesc();
}
