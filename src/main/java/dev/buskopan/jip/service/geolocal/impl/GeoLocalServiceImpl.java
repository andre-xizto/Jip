package dev.buskopan.jip.service.geolocal.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.IspResponse;
import dev.buskopan.jip.config.GeoLiteBundler;
import dev.buskopan.jip.service.geolocal.IGeoLocalService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
@Primary
public class GeoLocalServiceImpl implements IGeoLocalService {

    private final DatabaseReader cityDb;
    private final DatabaseReader asnDb;

    public GeoLocalServiceImpl(GeoLiteBundler geoLiteBundler) {
        this.cityDb = geoLiteBundler.cityDb();
        this.asnDb = geoLiteBundler.asnDb();
    }

    @Override
    public Map<String, String> getGeoLocal(String ip) {

        if (ip == null || ip.isEmpty()) {
            throw new IllegalArgumentException("IP cannot be empty or null");
        }

        Map<String, String> map = new TreeMap<>();

            map.putAll(getCity(ip));
            map.putAll(getAsn(ip));

        return map;
    }

    private Map<String, String> getCity(String ip) {
        try{
            Map<String, String> map = new TreeMap<>();
            CityResponse cityResponse = cityDb.city(InetAddress.getByName(ip));
            map.put("continent", cityResponse.getContinent().getName());
            map.put("country", cityResponse.getCountry().getName());
            map.put("postal", cityResponse.getPostal().getCode());
            map.put("city", cityResponse.getCity().getName());
            map.put("latitude", String.valueOf(cityResponse.getLocation().getLatitude()));
            map.put("longitude", String.valueOf(cityResponse.getLocation().getLongitude()));
            return map;
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, String> getAsn(String ip){
        try{
            Map<String, String> map = new TreeMap<>();
            AsnResponse asnResponse = asnDb.asn(InetAddress.getByName(ip));
            map.put("aso", asnResponse.getAutonomousSystemOrganization());
            map.put("asn", String.valueOf(asnResponse.getAutonomousSystemNumber()));
            return map;
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }
}
