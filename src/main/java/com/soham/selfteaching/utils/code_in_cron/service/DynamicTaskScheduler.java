package com.soham.selfteaching.utils.code_in_cron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicTaskScheduler {


    @Autowired
    private TaskScheduler taskScheduler;

    // Map to track scheduled tasks by ID
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    /**
     * Schedule a task using a cron expression.
     * @param taskId Unique identifier for the task
     * @param task The Runnable to execute
     * @param cronExpression Cron expression (e.g., "0 0/5 * * * *")
     */
    public void schedule(String taskId, Runnable task, String cronExpression) {
        ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(cronExpression));
        scheduledTasks.put(taskId, future);
    }

    /**
     * Cancel a scheduled task by its ID.
     * @param taskId The ID used when scheduling
     */
    public void cancel(String taskId) {
        ScheduledFuture<?> future = scheduledTasks.remove(taskId);
        if (future != null) {
            future.cancel(true);
        }
    }

    /**
     * Check if a task is currently scheduled.
     * @param taskId The ID of the task
     * @return true if scheduled, false otherwise
     */
    public boolean isScheduled(String taskId) {
        return scheduledTasks.containsKey(taskId);
    }

}
