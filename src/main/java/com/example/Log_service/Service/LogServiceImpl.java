package com.example.Log_service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Log_service.Model.LogEntry;
import com.example.Log_service.Repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {

      private final LogRepository repository;
      
        public LogServiceImpl(LogRepository repository) {
                this.repository = repository;
        }


        @Override
        public List<LogEntry> getAllLogs() {
                return repository.findAll();
        }

        @Override
        public LogEntry saveLog(LogEntry logEntry) {
                return repository.save(logEntry);
        }

        @Override
        public Optional<LogEntry> getLogById(Long id) {
                return repository.findById(id);
        }

        @Override
        public LogEntry updateLog(Long id, LogEntry updatedLog) {
            return repository.findById(id).map(existing -> {
                existing.setServiceName(updatedLog.getServiceName());
                existing.setAction(updatedLog.getAction());
                existing.setTimestamp(updatedLog.getTimestamp());
                existing.setDetails(updatedLog.getDetails());
                return repository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Log con ID " + id + " no encontrado"));
        }

        @Override
        public void deleteLog(Long id) {
                repository.deleteById(id);
        }

        @Override
        public List<LogEntry> getLogsBetween(LocalDateTime start, LocalDateTime end) {
                return repository.findByTimestampBetween(start.toString(), end.toString());
}

}