package com.example.Log_service.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import com.example.Log_service.Model.LogEntry;
import com.example.Log_service.Service.LogService;
import com.example.Log_service.assembler.LogEntryModelAssembler;

@WebMvcTest(LogController.class)
public class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogService logService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public LogEntryModelAssembler assembler() {
            return new LogEntryModelAssembler();
        }
    }

    @Test
    public void testGetAllLogs_returnsOkWithData() throws Exception {
        LogEntry log = new LogEntry(1L, "ServicioX", "AccionX", "01/01/2025", "Detalle de prueba");

        when(logService.getAllLogs()).thenReturn(List.of(log));

        mockMvc.perform(get("/api/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.logEntryModelList[0].serviceName").value("ServicioX"))
                .andExpect(jsonPath("$._embedded.logEntryModelList[0].action").value("AccionX"));
    }
}