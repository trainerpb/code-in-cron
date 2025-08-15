package com.soham.selfteaching.utils.code_in_cron.controller;

import com.soham.selfteaching.utils.code_in_cron.model.ScheduledJob;
import com.soham.selfteaching.utils.code_in_cron.service.ScheduledJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class UIController {

    private final ScheduledJobService scheduledJobService;
    /**
     * Handles the root request for the UI and returns the index view.
     *
     * @return ModelAndView object containing the index view and a new ScheduledJob object.
     */
    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("job", new ScheduledJob());
        modelAndView.addObject("jobs", scheduledJobService.getAllJobs());
        return modelAndView;
    }

    @PostMapping
    public String save(@ModelAttribute ScheduledJob job) {
        scheduledJobService.saveJob(job);
        return "redirect:/ui";
    }
}
