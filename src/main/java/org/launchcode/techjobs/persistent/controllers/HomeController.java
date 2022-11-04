package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "My Jobs");
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }


    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }


    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }


        // get and check if Employer object exists and set newjobs employer object to it
        Optional<Employer> optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = optEmployer.get();
            newJob.setEmployer(employer);
            //newJob.setEmployer(employer);
        }


        System.out.println(skills);
        List<Skill> skill_list = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skill_list);

        System.out.println(newJob.getId());
        System.out.println(newJob.getSkills());
        System.out.println(newJob.getEmployer());
        System.out.println(newJob.getName());

        jobRepository.save(newJob);
        model.addAttribute("job", jobRepository.findAll());

        return "redirect:";
    }



    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        model.addAttribute("title", "My Jobs");
//      model.addAttribute("jobs", jobRepository.findAll());
        Optional<Job> jobs = jobRepository.findById(jobId);
        if (jobs.isPresent()) {
            Job job = jobs.get();
            model.addAttribute("job", job);
        }

        return "view";
    }


}
