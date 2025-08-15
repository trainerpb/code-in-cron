package com.soham.selfteaching.utils.code_in_cron.repository;

import com.soham.selfteaching.utils.code_in_cron.model.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJob,Long> {
}
