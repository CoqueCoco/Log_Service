package com.example.Log_service.Model;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogEntryModel extends RepresentationModel<LogEntryModel>{

    private Long id;
    private String serviceName;
    private String action;
    private String timestamp;
    private String details;

    public LogEntryModel(LogEntry logEntry) {
        this.id = logEntry.getId();
        this.serviceName = logEntry.getServiceName();
        this.action = logEntry.getAction();
        this.timestamp = logEntry.getTimestamp().toString();
        this.details = logEntry.getDetails();
    }

}