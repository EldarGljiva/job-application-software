package com.eldar.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAllJobs() {
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<>("Job added succesfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
       if(job!=null)
           return new ResponseEntity<>(job, HttpStatus.OK);
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        String response = jobService.deleteJob(id);
        if(response!=null){
            return new ResponseEntity<>("Job Deleted Succesfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with specified id not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        String response = jobService.updateJob(id, updatedJob);
        if(response!=null){
            return new ResponseEntity<>("Job Updated Succesfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Job with specified id not found", HttpStatus.NOT_FOUND);
    }


}
