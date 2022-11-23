package org.landing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CheckinController {

    private final LocationService locationService;

    @PostMapping("/checkIn")
    public ResponseEntity<String> checkIn(@RequestBody Location location) {
        locationService.checkIn(location);
        return ResponseEntity.ok("Success");
    }

}
