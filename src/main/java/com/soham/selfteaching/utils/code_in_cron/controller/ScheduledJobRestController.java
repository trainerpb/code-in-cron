package com.soham.selfteaching.utils.code_in_cron.controller;

import com.soham.selfteaching.utils.code_in_cron.model.ScheduledJob;
import com.soham.selfteaching.utils.code_in_cron.service.ScheduledJobCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class ScheduledJobRestController {
    private final ScheduledJobCrudService scheduledJobCrudService;

    @GetMapping
    public ResponseEntity<List<ScheduledJob>> getAllJobs() {
        return ResponseEntity.ok(StreamSupport.stream(scheduledJobCrudService.getAllJobs().spliterator(), false).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledJob> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(scheduledJobCrudService.getJob(id));
    }
    @GetMapping("/trigger/{id}")
    public ResponseEntity<?> trigger(@PathVariable Long id) {
        try {
            scheduledJobCrudService.triggerJob(id);
            return ResponseEntity.ok().build();
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<ScheduledJob> createJob(@RequestBody ScheduledJob job) {
        scheduledJobCrudService.saveJob(job);
        return ResponseEntity.ok(job);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduledJob> updateJob(@PathVariable Long id, @RequestBody ScheduledJob job) {
        job.setId(id);
        scheduledJobCrudService.saveJob(job);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        scheduledJobCrudService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}

