package dev.buskopan.jip.service.geolocal;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.util.Map;

public interface IGeoLocalService {
    Map<String, String> getGeoLocal(String ip) throws IOException, GeoIp2Exception;
}
