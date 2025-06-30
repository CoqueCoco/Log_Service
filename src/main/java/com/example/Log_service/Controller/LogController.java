package com.example.Log_service.Controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Log_service.Model.LogEntry;
import com.example.Log_service.Model.LogEntryModel;
import com.example.Log_service.Service.LogService;
import com.example.Log_service.assembler.LogEntryModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("api/logs")
public class LogController {

    private final LogService logService;
    private final LogEntryModelAssembler assembler;

    public LogController(LogService logService, LogEntryModelAssembler assembler) {
        this.logService = logService;
        this.assembler = assembler;
    }

@GetMapping("/{id}")
public LogEntryModel getLogById(@PathVariable Long id) {
    LogEntry logEntry = logService.getLogById(id)
        .orElseThrow(() -> new RuntimeException("Log con ID " + id + " no encontrado"));
    return assembler.toModel(logEntry);
}

@GetMapping
public CollectionModel<LogEntryModel> getLogs() {
    List<LogEntryModel> logs = logService.getAllLogs().stream()
        .map(assembler::toModel)
        .toList();

    return CollectionModel.of(logs,
        linkTo(methodOn(LogController.class).getLogs()).withSelfRel(),
        linkTo(methodOn(LogController.class).createLog(null)).withRel("create")
    );
}

@PostMapping
public LogEntryModel createLog(@RequestBody LogEntry logEntry) {
    LogEntry savedLog = logService.saveLog(logEntry);
    return assembler.toModel(savedLog);
}

@PutMapping("/{id}")
public LogEntryModel updateLog(@PathVariable Long id, @RequestBody LogEntry logEntry) {
    LogEntry updatedLog = logService.updateLog(id, logEntry);
    return assembler.toModel(updatedLog);
}

@DeleteMapping("/{id}")
public EntityModel<?> deleteLog(@PathVariable Long id) {
    logService.deleteLog(id);
    return EntityModel.of("Log eliminado con éxito",
        linkTo(methodOn(LogController.class).getLogs()).withRel("logs"));
}
}