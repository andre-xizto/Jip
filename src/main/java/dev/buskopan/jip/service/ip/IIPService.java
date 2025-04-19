package dev.buskopan.jip.service.ip;

import jakarta.servlet.http.HttpServletRequest;

public interface IIPService {
    String getIp(HttpServletRequest request);
}
