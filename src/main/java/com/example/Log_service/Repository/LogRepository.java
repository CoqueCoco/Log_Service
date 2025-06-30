package com.example.Log_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Log_service.Model.LogEntry;

public interface LogRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findByTimestampBetween(String start, String end);

}
