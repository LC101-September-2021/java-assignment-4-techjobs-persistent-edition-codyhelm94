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


    //Part 3.1 Add a field employerRepository annotated with @Autowired.
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

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
        model.addAttribute(new Job());
        //Part 3.2 A user will select an employer when they create a job. Add the employer data from employerRepository into the form template. The add job form already includes an employer selection option. Be sure your variable name for the employer data matches that already used in templates/add.
        model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {


        //Part 3.4 In processAddJobForm, add code inside of this method to select the employer object that has been chosen to be affiliated with the new job. You will need to select the employer using the request parameter you’ve added to the method.
        Optional<Employer> employerResult=employerRepository.findById(employerId);
        Employer result=employerResult.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setEmployer(result);
        newJob.setSkills(skillObjs);
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob=jobRepository.findById(jobId);
        if(optJob.isPresent()){
            Job job=(Job) optJob.get();
            model.addAttribute("job",job);
            return "view";
        }
        return "view";
    }


}
