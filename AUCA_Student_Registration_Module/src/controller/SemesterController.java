package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.Semester;
import repository.HibernateUtil;


public class SemesterController {

    public Semester registerSemester(Semester SemesterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.save(SemesterObj);
            trans.commit();
            ss.close();
            return SemesterObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Semester updateSemester(Semester SemesterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(SemesterObj);
            trans.commit();
            ss.close();
            return SemesterObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteSemester(Semester SemesterObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(SemesterObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }  
    
    public Semester searchSemester(Semester sem){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Semester theSem= (Semester) ss.get(Semester.class, sem.getSemId());
            ss.beginTransaction().commit();
            ss.close();
            return theSem;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public Semester searchSemesterByName(Semester sem){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<Semester> query = ss.createQuery("FROM Semester WHERE semName = :name", Semester.class);
            query.setParameter("name", sem.getSemName());
            Semester theUnit = query.uniqueResult();
            ss.close();
            return theUnit;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
        
    

    public List<Semester> allSemesters() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Semester> query = session.createQuery("FROM Semester s", Semester.class);
            List<Semester> Semesters = query.list();
            session.close();
            return Semesters;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
}
