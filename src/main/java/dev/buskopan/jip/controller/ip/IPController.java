package dev.buskopan.jip.controller.ip;

import dev.buskopan.jip.controller.ip.dto.IPResponse;
import dev.buskopan.jip.service.IIPService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/ip")
public class IPController {

    private final IIPService ipService;

    public IPController(IIPService ipService) {
        this.ipService = ipService;
    }

    @GetMapping
    public ResponseEntity<?> getIp(HttpServletRequest request) {
        String ip = ipService.getIp(request);
        IPResponse response = new IPResponse(ip);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
