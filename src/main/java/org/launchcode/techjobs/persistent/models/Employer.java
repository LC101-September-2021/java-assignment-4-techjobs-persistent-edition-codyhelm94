package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {


    private String location;

    @OneToMany(mappedBy="employer")
    private List<Job> jobs=new ArrayList<>();

    //No Arg Constructor for Hibernate
    public Employer(){}

    //employer can only have one location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
