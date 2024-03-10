package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.AcademicUnit;
import domain.Course;
import domain.CourseDefinition;
import domain.CourseDefinition;
import repository.HibernateUtil;


public class CourseDefinitionController {

    public CourseDefinition registerCourseDefinition(CourseDefinition CourseDefinitionObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.persist(CourseDefinitionObj);
            trans.commit();
            ss.close();
            return CourseDefinitionObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public CourseDefinition updateCourseDefinition(CourseDefinition CourseDefinitionObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(CourseDefinitionObj);
            trans.commit();
            ss.close();
            return CourseDefinitionObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteCourseDefinition(CourseDefinition CourseDefinitionObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(CourseDefinitionObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 

    public CourseDefinition searchCourseDefinition(CourseDefinition crsDef){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            CourseDefinition theCourseDef = (CourseDefinition) ss.get(CourseDefinition.class, crsDef.getCourseDefCode());
            ss.beginTransaction().commit();
            ss.close();
            return theCourseDef;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public CourseDefinition searchCourseDefinitionByCode(CourseDefinition courseDef){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<CourseDefinition> query = ss.createQuery("FROM CourseDefinition WHERE courseDefCode = :code", CourseDefinition.class);
            query.setParameter("code", courseDef.getCourseDefCode());
            CourseDefinition theCourseDefinition= query.uniqueResult();
            ss.close();
            return theCourseDefinition;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<CourseDefinition> allCourseDefinitions() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<CourseDefinition> query = session.createQuery("FROM CourseDefinition s", CourseDefinition.class);
            List<CourseDefinition> CourseDefinitions = query.list();
            session.close();
            return CourseDefinitions;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
    
    
    
}
