package dev.buskopan.jip.config;

import com.maxmind.geoip2.DatabaseReader;

public record GeoLiteBundler(DatabaseReader cityDb, DatabaseReader asnDb) {
}
