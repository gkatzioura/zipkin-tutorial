package org.location;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.domain.geo.GeoLocation;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    public static final String VENUS_VISITED = "venues_visited";
    private final GeoOperations<String, String> geoOperations;

    public GeoService(GeoOperations<String, String> geoOperations) {
        this.geoOperations = geoOperations;
    }

    public void add(Location location) {
        Point point = new Point(location.getLng(), location.getLat());
        geoOperations.add(VENUS_VISITED, point, location.getName());

    }

    public List<String> nearByVenues(Double lng, Double lat, Double kmDistance) {
        Circle circle = new Circle(new Point(lng, lat), new Distance(kmDistance, Metrics.KILOMETERS));
        GeoResults<RedisGeoCommands.GeoLocation<String>> res = geoOperations.radius(VENUS_VISITED, circle);
        return res.getContent().stream()
                  .map(GeoResult::getContent)
                  .map(GeoLocation::getName)
                  .collect(Collectors.toList());
    }

}
