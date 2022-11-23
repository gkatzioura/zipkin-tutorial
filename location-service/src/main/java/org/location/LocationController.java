package org.location;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final GeoService geoService;

    public LocationController(GeoService geoService) {
        this.geoService = geoService;
    }

    @PostMapping("/location")
    public ResponseEntity<String> addLocation(@RequestBody Location location) {
        geoService.add(location);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/location/nearby")
    public ResponseEntity<List<String>> locations(Double lng, Double lat, Double km) {
        List<String> locations = geoService.nearByVenues(lng, lat, km);
        return ResponseEntity.ok(locations);
    }

}
