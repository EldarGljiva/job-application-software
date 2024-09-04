package com.eldar.firstjobapp.job.impl;

import com.eldar.firstjobapp.job.Job;
import com.eldar.firstjobapp.job.JobRepository;
import com.eldar.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    //private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id){
    return jobRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteJob(Long id){
        jobRepository.deleteById(id);
        return "Job deleted Succesfully";
    }

    @Override
    public String updateJob(Long id, Job updatedJob) {
        return jobRepository.findById(id)
                .map(existingJob -> {
                    updatedJob.setId(id); // Ensure the ID remains the same
                    jobRepository.save(updatedJob); // Save the updated job
                    return "Job updated successfully";
                })
                .orElse("Job with specified id not found");
    }



}
