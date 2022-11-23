package org.landing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LocationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${location.endpoint}")
    private String locationEndpoint;

    public void checkIn(Location location) {
        restTemplate.postForLocation(locationEndpoint, location);
    }

}
