/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvut.fel.dbs.lib.dao;

import cvut.fel.dbs.lib.model.AutoCourse;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author rufus
 */
public class AutoCourseDao {
    private EntityManager em;

    public AutoCourseDao() {
    }

    public AutoCourseDao(EntityManager em) {
        this.em = em;
    }

    public List<AutoCourse> findAll(){
        return em.createQuery("SELECT a FROM AutoCourse a", AutoCourse.class).getResultList();
    }
    
    public AutoCourse merge(AutoCourse a){
        return em.merge(a);
    }
}
