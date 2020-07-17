package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.Instructor;

import javax.persistence.EntityManager;
import java.util.List;

public class InstructorDao {
    private EntityManager em;

    public InstructorDao() {
    }

    public InstructorDao(EntityManager em) {
        this.em = em;
    }

    public List<Instructor> findAll(){
        return em.createQuery("SELECT p FROM Instructor p", Instructor.class).getResultList();
    }

    public void create(Instructor p){
        em.persist(p);
    }

    public Instructor find(Long personId){
        return em.find(Instructor.class, personId);
    }

    public Instructor merge(Instructor p){
        return em.merge(p);
    }

    public void delete(Instructor p){
        em.remove(p);
    }
    
}
