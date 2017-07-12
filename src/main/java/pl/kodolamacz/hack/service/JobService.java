package pl.kodolamacz.hack.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.kodolamacz.hack.model.Job;
import pl.kodolamacz.hack.service.repository.JobRepository;

import java.util.List;

/**
 * Created by Lukasz on 12.07.2017.
 */
public class JobService {


    @Autowired
    JobRepository jobRepository;

    public void addNewJob (Job job) {jobRepository.save(job);};
    public void updateJob (Job job){jobRepository.save(job);
    }

    public Job findJobById(Long id){
        return jobRepository.findById(id);
    }

    public List<Job> findJobsByName(String name){
        return jobRepository.findByName(name);
    }

    public List<Job> findJobsByNameLike(String nameSubstring){
        return jobRepository.getByNameLike(nameSubstring);
    }

    public void deleteEmployerProfile(Job job){jobRepository.delete(job);
    }

    public void deleteJobProfileById(Long id){
        jobRepository.delete(id);
    }

}