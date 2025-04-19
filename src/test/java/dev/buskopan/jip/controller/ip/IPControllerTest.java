package dev.buskopan.jip.controller.ip;

import dev.buskopan.jip.service.ip.IIPService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(IPController.class)
class IPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IIPService ipService;

    @Test
    void shouldReturnIp() throws Exception {
        when(ipService.getIp(any())).thenReturn("127.0.0.1");

        mockMvc.perform(get("/api/ip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ip").value("127.0.0.1"));
    }

    @Test
    void shouldReturnNull() throws Exception {
        when(ipService.getIp(any())).thenReturn(null);

        mockMvc.perform(get("/api/ip"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ip").doesNotExist());
    }
}