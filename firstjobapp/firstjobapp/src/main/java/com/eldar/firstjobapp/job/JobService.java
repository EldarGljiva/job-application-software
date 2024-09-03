package com.eldar.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    String deleteJob(Long id);
    String updateJob(Long id, Job updatedJob);
}
