package org.launchcode.techjobs.persistent.models;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

import java.util.Set;
import java.util.List;
import java.util.Collection;

@Entity
public class Employer extends AbstractEntity {


    @OneToMany()
    @JoinColumn(name="employer_id")
    public final List<Job> jobs = new ArrayList<>();

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String location;

    public Employer() {
    }

    public Employer(String location) {
        super();
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
