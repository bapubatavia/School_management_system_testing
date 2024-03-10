package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.Teacher;
import domain.Teacher;
import repository.HibernateUtil;


public class TeacherController {

    public Teacher registerTeacher(Teacher TeacherObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.persist(TeacherObj);
            trans.commit();
            ss.close();
            return TeacherObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Teacher updateTeacher(Teacher TeacherObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(TeacherObj);
            trans.commit();
            ss.close();
            return TeacherObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteTeacher(Teacher TeacherObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(TeacherObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 
    
    public Teacher searchTeacherByCode(Teacher teach){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<Teacher> query = ss.createQuery("FROM Teacher WHERE teacherCode = :code", Teacher.class);
            query.setParameter("code", teach.getTeacherCode());
            Teacher theTeacher= query.uniqueResult();
            ss.close();
            return theTeacher;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Teacher> allTeachers() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Teacher> query = session.createQuery("FROM Teacher s", Teacher.class);
            List<Teacher> Teachers = query.list();
            session.close();
            return Teachers;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
}
