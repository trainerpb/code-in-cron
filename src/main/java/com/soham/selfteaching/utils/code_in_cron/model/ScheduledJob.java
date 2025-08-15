package com.soham.selfteaching.utils.code_in_cron.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.util.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class ScheduledJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String description;
    private String cronExpression;
    private String code;
}
