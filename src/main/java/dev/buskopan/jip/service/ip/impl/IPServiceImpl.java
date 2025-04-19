package dev.buskopan.jip.service.ip.impl;

import dev.buskopan.jip.service.ip.IIPService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class IPServiceImpl implements IIPService {

    private final Logger logger = LoggerFactory.getLogger(IPServiceImpl.class);

    @Override
    public String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
            logger.info("Returning IP from request");
            return ip;
        }

        logger.info("Returning IP from X-Forwarded-For");
        return ip.split(",")[0];
    }
}

