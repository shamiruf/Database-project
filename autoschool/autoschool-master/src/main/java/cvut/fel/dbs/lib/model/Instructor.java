package cvut.fel.dbs.lib.model;

import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;

@Entity
public class Instructor {

    @Id
    @GeneratedValue
    private Long personId;

    private String name;

    private Integer employeeNumber;

    private String web;

    private Integer phoneNumber;

    private Integer salary;
    
    public Instructor() {
        
    }
    

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "autocourse_instructor",
            joinColumns = @JoinColumn(name = "instructor", referencedColumnName = "personid"),
            inverseJoinColumns = @JoinColumn(name = "autocouse", referencedColumnName = "id")
    )
    private List<AutoCourse> instructoredAutoCourses = new ArrayList<AutoCourse>();
    
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    
    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Employee Number
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    // Web
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    // Phone Number
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //Salary
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    public List<AutoCourse> getInstructoredAutoCourses() {
        return instructoredAutoCourses;
    }

    public void setInstructoredAutoCourses(List<AutoCourse> instructoredAutoCourses) {
        this.instructoredAutoCourses = instructoredAutoCourses;
    }
    
    // Add autoCourse to Instructor
    public void addAutoCourse(AutoCourse ac) {
        instructoredAutoCourses.add(ac);
        ac.getInstructors().add(this);
    }
    
    // Delete autoCourse from Instructor
    public void deleteAutoCourse(AutoCourse ac) {
        instructoredAutoCourses.remove(ac);
        ac.getInstructors().remove(this);
    }
    
    @Override
    public String toString() {
        return "Instructor{" +
                "personId= " + personId +
                ", name= " + name +
                ", employee number= " + employeeNumber +
                ", web= " + web +
                ", phone number= " + phoneNumber +
                ", salary= " + salary +
                "}";
    }
}
