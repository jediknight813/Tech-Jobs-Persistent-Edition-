package org.launchcode.techjobs.persistent.models;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Skill extends AbstractEntity {

    // variables
    @ManyToMany(mappedBy = "skills")
    private List<Job> jobs = new ArrayList<>();

    @NotEmpty
    @Size(min = 1, max = 100)
    private String description;


    // constructors
    public Skill() {}
    public Skill(String description) {
        this.description = description;
    }



    // getters and setters
    public List<Job> getJobs() {
        return jobs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}