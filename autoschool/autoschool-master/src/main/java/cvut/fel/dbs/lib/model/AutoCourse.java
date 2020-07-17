package cvut.fel.dbs.lib.model;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
public class AutoCourse {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String description;

    private String duration;

    private String cost;
    
    public AutoCourse() {
        
    }
    
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "instructoredAutoCourses")
    private List<Instructor> instructors = new ArrayList<Instructor>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Duration
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // Cost
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


    public List<Instructor> getInstructors() {
        return instructors;
    }

    // Add instructor to autocourse
    public void addInstructors(Instructor instructor) {
        instructors.add(instructor);
        instructor.getInstructoredAutoCourses().add(this);
    }

    @Override
    public String toString() {
        return "AutoCourse{" +
                "id= " + id +
                ", code= " + code +
                ", description= " + description +
                ", duration= " + duration +
                ", cost= " + cost +
                "}";
    }
}
