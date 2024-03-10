package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.StudentRegistration;
import domain.AcademicUnit;
import domain.Course;
import domain.Semester;
import domain.Student;
import domain.StudentRegistration;
import repository.HibernateUtil;


public class StudentRegistrationController {

    public StudentRegistration saveStudentRegistration(StudentRegistration studentRegObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.save(studentRegObj);
            trans.commit();
            ss.close();
            return studentRegObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public StudentRegistration updateStudentRegistration(StudentRegistration studentRegObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(studentRegObj);
            trans.commit();
            ss.close();
            return studentRegObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteStudentRegistration(StudentRegistration studentRegObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(studentRegObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }  
    
    public StudentRegistration searchStudentRegistration(StudentRegistration stuReg){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            StudentRegistration theStuReg= (StudentRegistration) ss.get(StudentRegistration.class, stuReg.getRegId());
            ss.beginTransaction().commit();
            ss.close();
            return theStuReg;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public StudentRegistration searchStudentRegistrationByStudentId(Student student){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Query<StudentRegistration> query = ss.createQuery("FROM StudentRegistration WHERE student = :stu", StudentRegistration.class);
            query.setParameter("stu", student);
            StudentRegistration theStuReg = query.uniqueResult();
            ss.close();
            return theStuReg;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public List<StudentRegistration> allStudentRegistrations() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<StudentRegistration> query = session.createQuery("FROM StudentRegistration s", StudentRegistration.class);
            List<StudentRegistration> studentRegistrations = query.list();
            session.close();
            return studentRegistrations;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
    
    public List<StudentRegistration> getStudentsBySemester(Semester semester) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<StudentRegistration> query = session.createQuery("FROM StudentRegistration s WHERE s.semester = :semester", StudentRegistration.class);
            query.setParameter("semester", semester);
            List<StudentRegistration> studentRegistrations = query.list();
            session.close();
            return studentRegistrations;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<StudentRegistration> getStudentsBySemesterAndDepartment(Semester semester, AcademicUnit aUnit) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<StudentRegistration> query = session.createQuery("FROM StudentRegistration s WHERE s.semester = :semester AND s.depId = :department", StudentRegistration.class);
            query.setParameter("semester", semester);
            query.setParameter("department", aUnit);
            List<StudentRegistration> studentRegistrations = query.list();
            session.close();
            return studentRegistrations;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<StudentRegistration> getStudentsByCourseAndSemester(Semester semester, Course course) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<StudentRegistration> query = session.createQuery("SELECT sr FROM StudentRegistration sr JOIN sr.semester sem JOIN sr.courses c WHERE sem = :semester AND c = :course", StudentRegistration.class);
            query.setParameter("semester", semester);
            query.setParameter("course", course);
            List<StudentRegistration> studentRegistrations = query.list();
            session.close();
            return studentRegistrations;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    
    
}
