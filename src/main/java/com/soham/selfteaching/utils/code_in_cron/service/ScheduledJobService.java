package com.soham.selfteaching.utils.code_in_cron.service;

import com.soham.selfteaching.utils.code_in_cron.model.ScheduledJob;
import com.soham.selfteaching.utils.code_in_cron.repository.ScheduledJobRepository;
import com.soham.selfteaching.utils.lib.SourceToHiddenClassUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduledJobService {

    private final ScheduledJobRepository scheduledJobRepository;


    public void saveJob(ScheduledJob job) {
        log.info("Saving job: {}", job);
        scheduledJobRepository.save(job);
    }
    public ScheduledJob getJob(Long id) {
        log.info("Fetching job with id: {}", id);
        return scheduledJobRepository.findById(id).orElseThrow(()-> new RuntimeException("Job not found with id: " + id));
    }
    public void deleteJob(Long id) {
        log.info("Deleting job with id: {}", id);
        scheduledJobRepository.deleteById(id);
    }
    public void updateJob(ScheduledJob job) {
        log.info("Updating job: {}", job);
        scheduledJobRepository.save(job);
    }
    public Iterable<ScheduledJob> getAllJobs() {
        log.info("Fetching all jobs");
        return scheduledJobRepository.findAll();
    }

    public Object triggerJob(Long id) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ScheduledJob job = getJob(id);
        log.info("Triggering job: {}", job);
        SourceToHiddenClassUtils.executeCode(job.getCode(), "Job_"+id, getClass().getPackageName(),"executeJob");
        return "Job triggered successfully";
    }
}
