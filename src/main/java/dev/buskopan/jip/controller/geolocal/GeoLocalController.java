package dev.buskopan.jip.controller.geolocal;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import dev.buskopan.jip.service.geolocal.IGeoLocalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/geo")
public class GeoLocalController {

    private final IGeoLocalService geoLocalService;

    public GeoLocalController(IGeoLocalService geoLocalService) {
        this.geoLocalService = geoLocalService;
    }

    @GetMapping()
    public ResponseEntity<?> getGeoLocal(@RequestParam("ip") String ip) throws IOException, GeoIp2Exception {
        Map<String, String> geoLocal = geoLocalService.getGeoLocal(ip);
        return ResponseEntity.ok(geoLocal);
    }
}
