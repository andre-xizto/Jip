package dev.buskopan.jip.service.ip.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class IPServiceImplIntegrationTest {

    @Autowired
    private IPServiceImpl ipService;

    @Test
    void shouldReturnIp() {
        HttpServletRequest req = new MockHttpServletRequest();
        String ip = ipService.getIp(req);
        assertNotNull(ip);
    }
}