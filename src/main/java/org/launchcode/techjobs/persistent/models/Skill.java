package org.launchcode.techjobs.persistent.models;


import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    private String description;

    //Part 4.1 This field has a many-to-many type relationship with skills. You’ll need to add the @ManyToMany annotation with an argument mappedBy="skills" to ensure this mapping.
    @ManyToMany(mappedBy = "skills")
    private final List<Job> jobs=new ArrayList<>();

    //No arg constructor
    public Skill(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }
}