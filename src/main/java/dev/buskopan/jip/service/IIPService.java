package dev.buskopan.jip.service;

import jakarta.servlet.http.HttpServletRequest;

public interface IIPService {
    String getIp(HttpServletRequest request);
}
