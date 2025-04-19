package dev.buskopan.jip.service.ip.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IPServiceImplTest {

    @InjectMocks
    private IPServiceImpl ipService;

    @Mock
    private HttpServletRequest request;

    @Test
    void testSuccessGetIpFromRequest() {
        when(request.getRemoteAddr()).thenReturn("192.168.0.1");
        String ip = ipService.getIp(request);

        assertEquals("192.168.0.1", ip);
    }

    @Test
    void testSuccessGetIpFromHeader() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.0.2");
        String ip = ipService.getIp(request);

        assertEquals("192.168.0.2", ip);
    }

    @Test
    void testSuccessGetFirstIpFromHeader() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.0.2,192.168.0.3");
        String ip = ipService.getIp(request);

        assertEquals("192.168.0.2", ip);
    }

    @Test
    void testNullGetIp() {
        when(request.getRemoteAddr()).thenReturn(null);
        String ip = ipService.getIp(request);

        assertNull(ip);
    }

}