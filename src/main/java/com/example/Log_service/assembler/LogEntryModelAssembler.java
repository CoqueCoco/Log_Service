package com.example.Log_service.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import com.example.Log_service.Controller.LogController;
import com.example.Log_service.Model.LogEntry;
import com.example.Log_service.Model.LogEntryModel;

@Component
public class LogEntryModelAssembler extends RepresentationModelAssemblerSupport<LogEntry, LogEntryModel> {

    public LogEntryModelAssembler() {
        super(LogController.class, LogEntryModel.class);
    }

    @Override
    public LogEntryModel toModel(LogEntry logEntry) {
        LogEntryModel model = new LogEntryModel(logEntry);
        model.add(
            linkTo(methodOn(LogController.class).getLogById(logEntry.getId())).withSelfRel(),
            linkTo(methodOn(LogController.class).updateLog(logEntry.getId(), null)).withRel("update"),
            linkTo(methodOn(LogController.class).deleteLog(logEntry.getId())).withRel("delete"),
            linkTo(methodOn(LogController.class).createLog(null)).withRel("create"),
            linkTo(methodOn(LogController.class).getLogs()).withRel("logs")
        );
        return model;
    }
}
