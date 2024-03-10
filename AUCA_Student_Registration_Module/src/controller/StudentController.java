package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import domain.Student;
import domain.Student;
import repository.HibernateUtil;


public class StudentController {

    public Student registerStudent(Student studentObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.save(studentObj);
            trans.commit();
            ss.close();
            return studentObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Student updateStudent(Student studentObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.merge(studentObj);
            trans.commit();
            ss.close();
            return studentObj;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(Student studentObj){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ss.beginTransaction();
            ss.remove(studentObj);
            trans.commit();
            ss.close();         
        }catch(Exception ex){
            ex.printStackTrace();
        }
    } 
    
    public Student searchStudent(Student student){
        try{
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Student theStudent= (Student) ss.get(Student.class, student.getStudentId());
            ss.beginTransaction().commit();
            ss.close();
            return theStudent;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public List<Student> allStudents() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("FROM Student s", Student.class);
            List<Student> students = query.list();
            session.close();
            return students;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
}
