package com.example.Log_service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.Log_service.Model.LogEntry;

public interface LogService {

        List<LogEntry> getAllLogs();

        LogEntry saveLog(LogEntry logEntry);

        Optional<LogEntry> getLogById(Long id);

        LogEntry updateLog(Long id, LogEntry updatedLog);

        void deleteLog(Long id);

        List<LogEntry> getLogsBetween(LocalDateTime start, LocalDateTime end);
}
