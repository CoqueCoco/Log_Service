package com.example.Log_service.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.Log_service.Model.LogEntry;
import com.example.Log_service.Repository.LogRepository;

public class LogServiceImplTest {

    @Test
    void testGetAllLogs() {
        LogRepository mockRepo = mock(LogRepository.class);
        LogServiceImpl service = new LogServiceImpl(mockRepo);

        LogEntry log = new LogEntry(1L, "AuthService", "Login", "10/02/2024", "Usuario accedió correctamente");
        when(mockRepo.findAll()).thenReturn(List.of(log));

        List<LogEntry> result = service.getAllLogs();
        
        assertEquals(1, result.size());
        assertEquals("AuthService", result.get(0).getServiceName());
    }
    
}
