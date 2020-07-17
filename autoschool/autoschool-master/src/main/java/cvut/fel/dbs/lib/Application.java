package cvut.fel.dbs.lib;

import cvut.fel.dbs.lib.dao.AutoCourseDao;
import cvut.fel.dbs.lib.dao.InstructorDao;
import cvut.fel.dbs.lib.model.AutoCourse;
import cvut.fel.dbs.lib.model.Instructor;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Application {

    private EntityManagerFactory emf;
    private EntityManager em;
    private InstructorDao personDao;
    private AutoCourseDao courseDao;

    public void setup(){
        // create emf
        emf = Persistence.createEntityManagerFactory("pu");
        // create entity manager
        em = emf.createEntityManager();
        // create Person dao
        personDao = new InstructorDao(em);
        // create AutoCOurse dao
        courseDao = new AutoCourseDao(em);

    }

    public void close(){
        em.close();
        emf.close();
    }
    
    public void getTransactionBegin() {
        em.getTransaction().begin();
    }
    
    public void getTransactionCommit() {
        em.getTransaction().commit();
    }
    
    // Return list with all instructors
    public ArrayList<Instructor> getInstructorsList() {
        setup();
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        
        ArrayList<Instructor> insList = new ArrayList<Instructor>();
        
        for(Instructor p : people){
            insList.add(p);
        }
        
        return insList;
    }    
    
    // Return list with all autocourses
    public ArrayList<AutoCourse> getAutoCoursesList() {
        setup();
        em.getTransaction().begin();
        List<AutoCourse> courses = courseDao.findAll();
        em.getTransaction().commit();
        
        ArrayList<AutoCourse> courseList = new ArrayList<AutoCourse>();
        
        for(AutoCourse a : courses){
            courseList.add(a);
        }
        
        return courseList;
    }   
     
    
    
    public void createIns(Instructor instructor) {
        personDao.create(instructor);
    }
    
    public void deleteIns(Instructor instructor) {
        Instructor ins2 = personDao.find(instructor.getPersonId());
        personDao.delete(ins2);
    }
    
    public Instructor mergeIns(Instructor instructor) {
        Instructor ins2 = personDao.merge(instructor);
        return ins2;
    }
    
    public AutoCourse mergeAC(AutoCourse autocourse) {
        AutoCourse autocourse2 = courseDao.merge(autocourse);
        return autocourse2;
    }
    
    // Return Instructor with given id
    public Instructor findIns(Long personId) {
        setup();
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        Instructor ins2 = new Instructor();
        
        for(Instructor p : people){
            if (p.getPersonId().equals(personId)) {
                ins2 = p;
            } 
        }
        
        return ins2;
    }
    
    // Return AutoCourse with given id
    public AutoCourse findAutoCourse(Long acId) {
        setup();
        em.getTransaction().begin();
        List<AutoCourse> courses = courseDao.findAll();
        em.getTransaction().commit();
        AutoCourse ac = new AutoCourse();
        
        for(AutoCourse a : courses){
            if (a.getId().equals(acId)) {
                ac = a;
            } 
        }
        
        return ac;
    }
    
    // Return true if Instructor with this employee number exists
    // Return false if Instructor with this employee number doesn't exist
    public boolean findInsEmpNumber(Integer employeeNumber) {
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        for(Instructor p : people){
            if (p.getEmployeeNumber().equals(employeeNumber)) {
                return true;
            }
        }
        return false;
    }
    
    // Return true if Instructor with web exists
    // Return false if Instructor with this web doesn't exist
    public boolean findInsWeb(String web) {
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        for(Instructor p : people){
            if (p.getWeb().equals(web)) {
                return true;
            }
        }
        return false;
    }
    
    // Return true if Instructor with phoneNumber exists
    // Return false if Instructor with this phoneNumber doesn't exist
    public boolean findInsPhoneNumber(Integer phoneNumber) {
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        for(Instructor p : people){
            if (p.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
    
    // Return instructors id if Instructor exists
    // Return null if Instructor doesn't exist
    public Long findInstructorId(Instructor instructor) {
        em.getTransaction().begin();
        List<Instructor> people = personDao.findAll();
        em.getTransaction().commit();
        for(Instructor p : people){
            if (p.getName().equals(instructor.getName()) && p.getEmployeeNumber().equals(instructor.getEmployeeNumber()) 
                    && p.getWeb().equals(instructor.getWeb()) && p.getPhoneNumber().equals(instructor.getPhoneNumber()) 
                    && p.getSalary().equals(instructor.getSalary())) {
                return p.getPersonId();
            }
        }
        return null;
    }
    
    // Return true if instructor doesn't have autocourse and add course to instructor
    // Return false if instructor already has autocourse
    public Boolean addAutoCourseToInstructor(Instructor instructor, AutoCourse autocourse) {
        
        List<AutoCourse> acList = instructor.getInstructoredAutoCourses();
        for (AutoCourse a: acList) {
            if(a.getId().equals(autocourse.getId())) {
                return false;
            } 
        }
        instructor.addAutoCourse(autocourse);
        em.getTransaction().begin();
        em.merge(instructor);
        em.merge(autocourse);
        em.getTransaction().commit();
        return true;
    }
    
    // Return true if instructor has autocourse and delete this course from instructor
    // Return false if instructor doesn't have autocourse
    public Boolean deleteAutoCourseFromInstructor(Instructor instructor, AutoCourse autocourse) {
        List<AutoCourse> acList = instructor.getInstructoredAutoCourses();
        for (AutoCourse a: acList) {
            if(a.getId().equals(autocourse.getId())) {
                instructor.deleteAutoCourse(a);
                em.getTransaction().begin();
                em.merge(instructor);
                em.merge(autocourse);
                em.getTransaction().commit();
                return true;
            } 
        }
        return false;
    }
}


